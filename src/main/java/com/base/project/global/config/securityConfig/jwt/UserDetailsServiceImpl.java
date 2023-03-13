package com.base.project.global.config.securityConfig.jwt;


import com.base.project.domain.member.domain.Member;
import com.base.project.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Member member = userRepository.findById(Long.parseLong(username))
                .orElseThrow(() -> new UsernameNotFoundException("username = " + username));
        return User.withUsername(username)
                .password(member.getPasswordLogin().getPassword())
                .authorities(AuthorityUtils.NO_AUTHORITIES)
                .build();
    }
}