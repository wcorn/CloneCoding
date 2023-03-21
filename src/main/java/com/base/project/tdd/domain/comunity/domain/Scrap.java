package com.base.project.tdd.domain.comunity.domain;

import com.base.project.tdd.domain.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
public class Scrap implements Serializable {

    @Id
    @ManyToOne(fetch= LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Id
    @ManyToOne(fetch= LAZY)
    @JoinColumn(name="post_id")
    private Post post;
}
