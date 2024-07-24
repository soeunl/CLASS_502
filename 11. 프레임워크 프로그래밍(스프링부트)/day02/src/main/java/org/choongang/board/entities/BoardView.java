package org.choongang.board.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class BoardView {
    @EmbeddedId
    private BoardViewId id;
}

//@IdClass(BoardViewId.class)
//public class BoardView { // 복합키 만드는 과정
//    @Id
//    private long seq;
//
//    @Id
//    @Column(name = "_uid")
//    private int uid;
