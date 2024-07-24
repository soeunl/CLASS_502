package org.choongang.board.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode // 중복 제거의 기준
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BoardViewId {
    // 동일한것 판단하는 기준은 equals() & hashcode()
    // 중복을 제거하고 유일하게 만든다
    private long seq;
    @Column(name="_uid")
    private int uid;
}
