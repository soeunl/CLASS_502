package org.choongang.member.entities;

// 여기에 정의된 내용을 가지고 데이터를 담을 수 있는 객체를 만든다.
// 그것이 테이블이다? 테이블이 이 내용을 바탕으로 만들어진다.

import jakarta.persistence.*;
import lombok.Data;
import org.choongang.member.constants.Authority;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
// @Table(name="CH_MEMBER")
//@Table(indexes = {
//                    @Index(name = "idx_created_at_desc", columnList = "createdAt DESC"), // DB 컬럼이 아니고 엔티티 클래스 안에 정의된 속성명으로 해야한다!
//                    @Index(name="uq_email_password", columnList = "email, password", unique = true)})
public class Member {

    @Id @GeneratedValue
    private Long seq;
    private String email;
    private String password;
    private String userName;

    // @Lob
    @Transient // 매핑 무시, 내부에서 사용할 목적
    // 2차로 가공해서 사용할 예정
    // 임시로 이 내부에서 사용할 목적
    private String introduction;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;
}
