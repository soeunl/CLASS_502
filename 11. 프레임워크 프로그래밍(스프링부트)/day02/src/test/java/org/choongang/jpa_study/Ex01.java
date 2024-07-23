package org.choongang.jpa_study;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import org.choongang.member.entities.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
public class Ex01 {
    
//    @Autowired
//    private EntityManagerFactory emf; // EntityManager를 만들고 사용

//    @Autowired
    @PersistenceContext
    private EntityManager em;

    @Test
    void test1() {
        // EntityManager em = emf.createEntityManager(); // 상태 변화에 따라 쿼리 자동 실행
        // 이미 빈으로 만들어져 있다

        EntityTransaction tx = em.getTransaction();

        tx.begin(); // Transaction 시작
        Member member = new Member();
        member.setSeq(1L);
        member.setEmail("user01@test.org");
        member.setPassword("12345678");
        member.setUserName("사용자01");
        member.setCreatedAt(LocalDateTime.now());

        em.persist(member); // 영속성 안에 넣어준다.(변화 감지 메모리에 있다)
        // 영속성이 바로 변화 감지 상태이다. 상태 변화 감지를 통해 쿼리가 자동 수행
        // 관리해 달라고 넣어 주는 것이다

        em.flush(); // INSERT 쿼리 DB에 반영

        em.detach(member); // 영속 상태 분리 - 변화 감지 X
        // 이 이후에는 변화 감지를 하지 않는다
        // 의도치 않은 쿼리 수행을 방지하기 위함이다

        member.setUserName("수정된 사용자"); // 변경
        member.setModifiedAt(LocalDateTime.now());

        em.flush(); // UPDATE 쿼리 DB에 반영
        
        em.merge(member); // 분리된 영속 상태를 다시 변화 감지 상태(영속 상태)로 만들어 주는 것이다
        // 변화가 되었는지 확인 먼저 하고 감지하기 위해 select를 먼저 한번 실행한다
        // 변화가 있으면 변화 수행
        // 이전에 되지 않았던 update 쿼리가 다시 실행된다

//        em.remove(member); // 제거 상태, 제거는 되지 않고 상태만 변경
//        em.flush(); // DELETE 쿼리 DB에 반영

        tx.commit(); // commit
    }
}
