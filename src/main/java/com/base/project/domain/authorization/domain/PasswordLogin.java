package com.base.project.domain.authorization.domain;

import com.base.project.domain.member.domain.Member;
import com.base.project.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
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

}