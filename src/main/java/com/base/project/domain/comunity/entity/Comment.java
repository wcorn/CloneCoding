package com.base.project.domain.comunity.entity;

import com.base.project.domain.member.entity.Member;
import com.base.project.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseEntity {

    String content;

    boolean isAnonymous;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
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
