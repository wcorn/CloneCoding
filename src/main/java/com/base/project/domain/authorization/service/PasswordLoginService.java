package com.base.project.domain.authorization.service;

import com.base.project.domain.authorization.domain.PasswordLogin;
import com.base.project.domain.authorization.repository.PasswordLoginRepository;
import com.base.project.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.DomainEvents;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PasswordLoginService {
    private final PasswordLoginRepository passwordLoginRepository;
    private final PasswordEncoder passwordEncoder;

    public void createMember(String email, String password) throws NoSuchAlgorithmException {
        String salt = makeSalt();
        String encryptedPassword = encryptionPassword(password,salt);
        PasswordLogin passwordLogin = PasswordLogin.createPasswordMember(email,encryptedPassword,salt);
        passwordLoginRepository.save(passwordLogin);
    }
    private String makeSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        return new String(Base64.getEncoder().encode(bytes));
    }

    private String encryptionPassword(String Password,String salt) throws NoSuchAlgorithmException {
        return passwordEncoder.encode(Password+salt);
    }
}
