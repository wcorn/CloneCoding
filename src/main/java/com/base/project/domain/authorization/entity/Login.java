package com.base.project.domain.authorization.entity;

import com.base.project.domain.member.entity.Member;
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
public class Login extends BaseEntity {

    private String authType;

    private String authId;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
