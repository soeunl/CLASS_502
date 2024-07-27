package org.choongang.member.repositories;

import org.choongang.member.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
    // 자료형을 지네릭 타입으로 명시. 기본형으로 하면 바꾸는 작업이 필요하기 때문이다. 번거롭다.
    // Optional<Member> findByEmail(String email); // null처리 할때는Optional<Member>
    Member findByEmail(String email);
    // 다른 필드로 조회할 때는 쿼리 메서드를 만들어야 한다.
    // 기본키는 괜찮다.

    Page<Member> findByEmailContaining(String keyword, Pageable pageable);

    List<Member> findByEmailContainingAndUserNameContainingOrderByCreatedAtDesc(String key1, String key2);

    @Query("SELECT m FROM Member m WHERE m.email LIKE :k1 AND m.userName LIKE :k2 ORDER BY  m.createdAt DESC ")
        // 엔티티명으로 쓰지 않으면 오류 생긴다. 여기서 오류가 생겨도 실행 전까지는 발견이 힘들다
    List<Member> getMembers(@Param("k1")String key1, @Param("k2")String key2);
    // 두 개의 문자열 매개변수(key1, key2)를 받아 List<Member> 타입의 결과를 반환하는 메서드
}
