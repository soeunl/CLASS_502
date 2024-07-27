package org.choongang.board.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.choongang.global.entities.BaseEntity;
import org.choongang.member.entities.Member;

@Data
@Builder
@Entity // JPA의 어노테이션으로, 이 클래스가 데이터베이스의 테이블과 매핑됨을 나타냄
@AllArgsConstructor // 클래스의 모든 필드를 사용하여 생성자를 생성
@NoArgsConstructor // 매개변수가 없는 기본 생성자를 생성
// 이 두 애노테이션을 함께 사용하면, 클래스에 모든 필드를 매개변수로 받는 생성자와 매개변수가 없는 기본 생성자가 모두 자동으로 생성되어 편리하게 사용할 수 있음
public class BoardData extends BaseEntity {
    @Id @GeneratedValue // 엔티티의 기본 키로 사용되며, 데이터베이스에서 자동으로 생성되는 값
    private Long seq;

    @ManyToOne(fetch=FetchType.LAZY) // 엔티티(Member)와의 다대일 관계 / 지연로딩
    // many가 있는 쪽에 정의한다
    // member_seq (외래키가 엔티티명 기준으로 만들어진다) 엔티티명_기본키 속성명
    @JoinColumn(name = "mSeq") // 컬럼명 바꿀때 사용 / Member 엔티티와의 관계를 위한 외래 키 컬럼 이름을 지정
    private Member member;

    @Column(nullable = false) // null은 안되고 크기는 255로 한다.
    private String subject;

    @Lob // 대용량 데이터를 저장하기 위한 LOB(Large Object) 타입
    private String content;

//    @ManyToMany
//    private List<HashTag> tags;
}
