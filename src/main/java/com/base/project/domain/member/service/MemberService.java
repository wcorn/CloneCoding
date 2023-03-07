package com.base.project.domain.member.service;

import com.base.project.domain.member.dto.request.LogInRequstDto;
import com.base.project.domain.member.dto.request.SignUpRequestDto;
import com.base.project.domain.member.dto.response.LogInResponseDto;
import com.base.project.domain.member.entity.Member;
import com.base.project.domain.member.repository.MemberRepository;
import com.base.project.global.error.ErrorCode;
import com.base.project.global.common.exception.CustomException;
import com.base.project.global.config.SecurityConfig.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void userSignUp(SignUpRequestDto signUpRequestDto) {
        try {
            Member member = Member.builder()
                    .name(signUpRequestDto.getName())
                    .email(signUpRequestDto.getEmail())
                    .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                    .build();
            log.info("member signup userAcoount {}", member);
            Member saved = userRepository.save(member);
            log.info("member signup saved {}", saved);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PASSWORD_ENCRYPTION_ERROR);
        }
    }

    public Member findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    public void validationDuplicateEmail(String email) {
        Optional<Member> member = Optional.ofNullable(userRepository.findByEmail(email));
        if (member.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }
    }

    public LogInResponseDto userLogIn(LogInRequstDto request) {
        request.setPassword(request.getPassword());
        Optional<Member> member = checkPassword(request);
        String jwt = jwtTokenProvider.createAccessToken(Long.toString(member.get().getId()));
        return new LogInResponseDto(jwt);
    }

    public Optional<Member> checkPassword(LogInRequstDto logInRequstDto) {
        Optional<Member> member = Optional.ofNullable(userRepository.findByEmail(logInRequstDto.getEmail()));
        if (!passwordEncoder.matches(logInRequstDto.getPassword(),member.get().getPassword())) {
            throw new CustomException(ErrorCode.USER_FAILED_LOGIN);
        }
        return member;
    }
}
