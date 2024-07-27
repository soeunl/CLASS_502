package org.choongang.member.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

@Data
@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
public class MemberProfile {
    @Id @GeneratedValue // seq 필드가 엔티티의 기본 키(Primary Key)이며, 자동으로 생성되는 값임
    private Long seq;
    private String profileImage;
    private String status;

    @ToString.Exclude // ToString 추가 배제
    // 보통 관계의 주인이 아닌 쪽을 끊어준다. 주가 되는게 아니기 때문이다!
    @OneToOne(mappedBy = "profile") // Member 엔티티와 일대일 관계를 맺으며, 매핑된 필드 이름은 profile
    private Member member;
}