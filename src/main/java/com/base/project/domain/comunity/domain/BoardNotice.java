package com.base.project.domain.comunity.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
public class BoardNotice implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Id
    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
