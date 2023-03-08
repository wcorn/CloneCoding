package com.base.project.domain.comunity.entity;

import com.base.project.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
public class CommentLike implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
