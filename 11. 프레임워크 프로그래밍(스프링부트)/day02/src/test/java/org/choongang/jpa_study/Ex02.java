package org.choongang.jpa_study;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.choongang.member.entities.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@TestPropertySource(properties = "spring.profiles.active=test")
public class Ex02 {

    @PersistenceContext
    // @PersistenceContext를 사용하면 엔티티 매니저를 주입받아 데이터베이스와 상호작용할 수 있다.
    // Spring Framework에서 JPA를 사용할 때 @PersistenceContext를 사용하여 엔티티 매니저를 주입받고,
    // 이를 통해 데이터베이스에 접근하거나 쿼리를 실행할 수 있다.
    private EntityManager em;

    @BeforeEach
    void init() {
        for (long i = 1L; i <= 10L; i++) {
            Member member = new Member();
            member.setSeq(i);
            member.setEmail("user" + i + "@test.org");
            member.setPassword("12345678");
            member.setUserName("사용자" + i);
            member.setCreatedAt(LocalDateTime.now());
            em.persist(member); // 영속 상태로 만들기
        }
        em.flush(); // DB에 영구 반영
        em.flush(); // 영속 상태 엔티티 모두 비우기
    }

    @Test
    void test1() {
        Member member = em.find(Member.class, 1L);
        System.out.println(member);

        Member member2 = em.find(Member.class, 1L);
        System.out.println(member2);

        System.out.println(member == member2);
        System.out.println("member" + System.identityHashCode(member));
        System.out.println("member2" + System.identityHashCode(member2));

        member.setUserName("(수정)사용자1");

        //em.flush(); // UPDATE 쿼리 수행
        // 값 변경후, 삭제 상태 변경 후 해당 데이터를 조회 -> 암묵적으로 flush()
        Member member3 = em.find(Member.class, 1L);
        System.out.println(member3);

    }
}
// 영속성 상태 안에 있으면 DB까지 가지 않고 영속성 안에서 꺼내온다
// 1차 캐시의 역할을 담당한다
// 영속성 안에 없으면 쿼리를 수행한다
// find()메서드는 영속성 관리가 되지 않고 있는 신규 메서드일때만 DB에서 꺼내온다
// 그 외에는 영속성 안에서 가지고 오기 때문에 다 똑같은게 나온다
// 쿼리를 직접 짜는 것보다 관리를 맡기는 것이 더욱 효율적이다.
// 값 변경이나 삭제 후 해당 데이터를 조회하면 암묵적으로 flush()가 발생한다 -> 최종 상태가 조회 되어야 하기 때문이다

