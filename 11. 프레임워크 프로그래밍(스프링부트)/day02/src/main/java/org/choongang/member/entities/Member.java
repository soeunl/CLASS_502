package org.choongang.member.entities;

// 여기에 정의된 내용을 가지고 데이터를 담을 수 있는 객체를 만든다.
// 그것이 테이블이다? 테이블이 이 내용을 바탕으로 만들어진다.

import jakarta.persistence.*;
import lombok.*;
import org.choongang.board.entities.BoardData;
import org.choongang.global.entities.BaseEntity;
import org.choongang.member.constants.Authority;

import java.util.List;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
// @Table(name="CH_MEMBER")
//@Table(indexes = {
//                    @Index(name = "idx_created_at_desc", columnList = "createdAt DESC"), // DB 컬럼이 아니고 엔티티 클래스 안에 정의된 속성명으로 해야한다!
//                    @Index(name="uq_email_password", columnList = "email, password", unique = true)})
public class Member extends BaseEntity {

    @Id /* @GeneratedValue(strategy = GenerationType.AUTO) */ @GeneratedValue
    private Long seq;

    @Column(length = 60, nullable = false, unique = true)
    private String email;

    @Column(length = 65, nullable = false)
    private String password;

    @Column(length = 40, nullable = false, name = "name")
    private String userName;

    // @Lob
    @Transient // 매핑 무시, 내부에서 사용할 목적
    // 2차로 가공해서 사용할 예정
    // 임시로 이 내부에서 사용할 목적
    private String introduction;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_seq") // 여기 외래키가 만들어지고 이걸 바탕으로 조회한다.
    private MemberProfile profile;

    @ToString.Exclude // ToString 추가 배제
    @OneToMany (mappedBy = "member") // 꼭 관계의 주인 설정!
    // One이 멤버, Many가 Board-Data
    // 이때는 관계의 주인을 꼭 설정해야 한다.(외래키가 있는 쪽이 관계의 주인)
    // 이렇게 해야지 데이터를 가지고 올 수 있다.
    private List<BoardData> items;
}
