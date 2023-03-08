package com.base.project.domain.member.entity;

import com.base.project.domain.comunity.entity.Board;
import com.base.project.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class School  extends BaseEntity {
    private String name;
    private String addressForm;

    @OneToMany(mappedBy = "school")
    @Builder.Default
    @ToString.Exclude
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "school")
    @Builder.Default
    @ToString.Exclude
    private List<Board> boards = new ArrayList<>();
}
