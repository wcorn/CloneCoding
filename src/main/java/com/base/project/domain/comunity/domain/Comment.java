package com.base.project.domain.comunity.domain;

import com.base.project.domain.member.domain.Member;
import com.base.project.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;
import static javax.persistence.FetchType.LAZY;
@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseEntity {

    String content;

    boolean isAnonymous;

    @ManyToOne(fetch= LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch= LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch= LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    @Builder.Default
    @ToString.Exclude
    private List<Comment> child = new ArrayList<>();

    @OneToMany(mappedBy = "comment")
    @Builder.Default
    @ToString.Exclude
    private List<CommentLike> commentLikes = new ArrayList<>();
}
