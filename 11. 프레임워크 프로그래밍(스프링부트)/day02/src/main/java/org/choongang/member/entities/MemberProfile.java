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
    @Id @GeneratedValue
    private Long seq;
    private String profileImage;
    private String status;

    @ToString.Exclude // ToString 추가 배제
    // 보통 관계의 주인이 아닌 쪽을 끊어준다. 주가 되는게 아니기 때문이다!
    @OneToOne(mappedBy = "profile")
    private Member member;
}