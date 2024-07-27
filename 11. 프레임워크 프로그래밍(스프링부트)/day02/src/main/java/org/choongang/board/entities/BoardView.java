package org.choongang.board.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class BoardView {
    @EmbeddedId // BoardViewId라는 클래스를 임베디드 아이디로 사용한다는 것
    // 임베디드 아이디는 복합 키를 사용할 때 사용되는 방법으로, 하나 이상의 필드를 조합하여 엔티티의 기본 키를 구성
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
