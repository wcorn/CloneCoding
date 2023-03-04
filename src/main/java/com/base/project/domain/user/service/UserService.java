package com.base.project.domain.user.service;

import com.base.project.domain.user.dto.request.LogInRequstDto;
import com.base.project.domain.user.dto.request.SignUpRequestDto;
import com.base.project.domain.user.dto.response.LogInResponseDto;
import com.base.project.domain.user.entity.UserAccount;
import com.base.project.domain.user.repository.UserRepository;
import com.base.project.global.common.api.ErrorCode;
import com.base.project.global.common.exception.CustomException;
import com.base.project.global.config.SecurityConfig.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void userSignUp(SignUpRequestDto signUpRequestDto) {
        try {
            UserAccount userAccount = UserAccount.builder()
                    .name(signUpRequestDto.getName())
                    .email(signUpRequestDto.getEmail())
                    .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                    .build();
            log.info("user signup userAcoount {}", userAccount);
            UserAccount saved = userRepository.save(userAccount);
            log.info("user signup saved {}", saved);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PASSWORD_ENCRYPTION_ERROR);
        }
    }

    public UserAccount findUserById(Long id) {
        UserAccount user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return user;
    }

    public void validationDuplicateEmail(String email) {
        Optional<UserAccount> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (!user.isEmpty()) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }
    }

    public LogInResponseDto userLogIn(LogInRequstDto request) {
        request.setPassword(request.getPassword());
        Optional<UserAccount> user = checkPassword(request);
        String jwt = jwtTokenProvider.createAccessToken(Long.toString(user.get().getId()));
        return new LogInResponseDto(jwt);
    }

    public Optional<UserAccount> checkPassword(LogInRequstDto logInRequstDto) {
        Optional<UserAccount> user = Optional.ofNullable(userRepository.findByEmail(logInRequstDto.getEmail()));
        if (!passwordEncoder.matches(logInRequstDto.getPassword(),user.get().getPassword())) {
            throw new CustomException(ErrorCode.USER_FAILED_LOGIN);
        }
        return user;
    }
}
