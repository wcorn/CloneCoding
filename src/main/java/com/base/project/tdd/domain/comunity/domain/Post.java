package com.base.project.tdd.domain.comunity.domain;

import com.base.project.tdd.domain.member.domain.Member;
import com.base.project.tdd.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Post extends BaseEntity {

    private String title;

    private String content;

    private boolean isQuestion;

    private boolean isAnonymous;

    @ManyToOne(fetch= LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post")
    @Builder.Default
    @ToString.Exclude
    private List<PostImage> postImages = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    @Builder.Default
    @ToString.Exclude
    private List<PostLike> postLikes = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    @Builder.Default
    @ToString.Exclude
    private List<Scrap> scraps = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    @Builder.Default
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();


//    @OneToOne(mappedBy = "post")
//    private BoardNotice boardNotice;
}

