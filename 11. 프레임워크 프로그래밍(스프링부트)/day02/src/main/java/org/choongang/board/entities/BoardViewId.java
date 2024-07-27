package org.choongang.board.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode // 중복 제거의 기준
@AllArgsConstructor
@NoArgsConstructor
@Embeddable // 복합키 구성, 여기서 만든 복합키가 다른 엔티티의 ID로 사용됨
public class BoardViewId {
    // 동일한것 판단하는 기준은 equals() & hashcode()
    // 중복을 제거하고 유일하게 만든다
    // 복합키를 별도의 클래스로 정의한다!
    private long seq;
    @Column(name="_uid") // 데이터베이스 컬럼 이름 바꾸기
    private int uid;
}
