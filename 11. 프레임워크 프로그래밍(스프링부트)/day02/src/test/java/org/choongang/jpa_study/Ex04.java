package org.choongang.jpa_study;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.choongang.member.entities.Member;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest // 의존성 주입을 따로 할 필요 없이 다 가능하게 해줌
@Transactional
@TestPropertySource(properties = "spring.profiles.active=test")
public class Ex04 {

    @PersistenceContext // 의존성 주입
    private EntityManager em;

    @Test
    void test1() throws Exception{
        Member member = new Member();
        member.setEmail("user01@choongang.com");
        member.setPassword("12345678");
        member.setUserName("사용자01");

        em.persist(member);

        em.flush();

        em.clear(); // 영속성을 비워야지만 다시 조회하기 때문에 한다.

        member = em.find(Member.class, member.getSeq());
        System.out.printf("createdAt: %s, modifiedAt: %s%n", member.getCreatedAt(), member.getModifiedAt());

        Thread.sleep(5000);
        member.setUserName("수정한 사용자01");
        em.flush();
        em.clear();

        member = em.find(Member.class, member.getSeq());
        System.out.println(member);

        member = em.find(Member.class, member.getSeq());
        System.out.printf("createdAt: %s, modifiedAt: %s%n", member.getCreatedAt(), member.getModifiedAt());
    }
}
