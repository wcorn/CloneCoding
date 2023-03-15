package com.base.project.domain.authorization.domain;

import com.base.project.domain.member.domain.Member;
import com.base.project.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PasswordLogin extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "member_id")
    Member member;

    String email;

    String salt;

    String password;



    //== Create Method==//
    public static PasswordLogin createPasswordMember(String email, String password,String salt) throws NoSuchAlgorithmException {
        return PasswordLogin.builder()
                .member(Member.createMember())
                .email(email)
                .salt(salt)
                .password(password)
                .build();
    }


}