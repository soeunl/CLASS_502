package org.choongang.board.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HashTag {

    @Id
    private String tag;

//    @ManyToMany(mappedBy = "tags") // 외래키는 없지만 관계의 주인은 설정해야 한다.
//    @ToString.Exclude
//    // 여기서 관계의 주인은 BoardData의 tags
//    private List<BoardData> items;
}
