package com.base.project.domain.member.entity;

import com.base.project.domain.authorization.entity.Login;
import com.base.project.domain.authorization.entity.SelfLogin;
import com.base.project.domain.comunity.entity.*;
import com.base.project.global.common.entity.BaseEntity;
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
public class Member extends BaseEntity {

    private String email;

    private String password;

    private String name;

    private int age;

    @OneToOne(mappedBy = "member")
    private Login login;

    @OneToOne(mappedBy = "member")
    private SelfLogin selfLogin;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    @ToString.Exclude
    private List<Board> boards = new ArrayList<>();

    @ManyToOne(fetch= LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    @ToString.Exclude
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    @ToString.Exclude
    private List<PostLike> postLikes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    @ToString.Exclude
    private List<FavoriteBoard> favoriteBoards = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    @ToString.Exclude
    private List<Scrap> scraps = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    @ToString.Exclude
    private List<CommentLike> commentLikes = new ArrayList<>();
}
