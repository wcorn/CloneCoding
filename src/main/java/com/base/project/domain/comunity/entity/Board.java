package com.base.project.domain.comunity.entity;


import com.base.project.domain.member.entity.Member;
import com.base.project.domain.member.entity.School;
import com.base.project.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;
import static javax.persistence.FetchType.LAZY;
@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)

public class Board extends BaseEntity {
    String title;
    String description;
    boolean is_anonymous;

    @ManyToOne(fetch= LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch= LAZY)
    @JoinColumn(name="school_id")
    private School school;

//    @OneToOne(mappedBy = "board")
//    private BoardNotice boardNotice;


}
