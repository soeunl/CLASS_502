package org.choongang.jpa_study;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.choongang.member.repositories.MemberProfileRepository;
import org.choongang.member.constants.Authority;
import org.choongang.member.entities.Member;
import org.choongang.member.entities.MemberProfile;
import org.choongang.member.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional // 관계매핑이나 지연로딩 시 항상 사용!
public class Ex10 {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberProfileRepository profileRepository;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void init() {
        MemberProfile profile = MemberProfile.builder()
                .profileImage("이미지")
                .status("상태")
                .build();

        profileRepository.saveAndFlush(profile);

        Member member = Member.builder()
                .email("user01@test.org")
                .password("12345678")
                .userName("사용자01")
                .authority(Authority.USER)
                .profile(profile) // 여기에 profile을 넣기 위해 profile을 먼저 등록!
                .build();

        memberRepository.saveAndFlush(member);

        em.clear();
    }

    @Test
    void test1() {
        Member member = memberRepository.findById(1L).orElse(null);
        MemberProfile profile = member.getProfile();
        System.out.println(profile);
    }

    @Test
    void test2() {
        MemberProfile profile = profileRepository.findById(1L).orElse(null);
        Member member = profile.getMember();
        System.out.println(profile);
        // 서로 양방향으로 조회할 때 순환참조 오류가 발생한다.
    }
}
