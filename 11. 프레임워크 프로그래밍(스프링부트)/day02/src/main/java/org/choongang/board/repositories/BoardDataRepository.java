package org.choongang.board.repositories;

import org.choongang.board.entities.BoardData;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BoardDataRepository extends JpaRepository<BoardData, Long>, QuerydslPredicateExecutor<BoardData> {
    // 자바 표준 프록시..?
    // 인터페이스로 만들어야 한다

    // JPQL
    @Query("SELECT b FROM BoardData b LEFT JOIN FETCH b.member")
    List<BoardData> getAllList(); // 이렇게 해두어도 엔티티에 정의한 매핑 전략으로 쿼리가 실행된다,,,?
    // 기본적으로 엔티티에 정의된 전략대로 쿼리가 수행된다.
    // FETCH를 쓰면 처음부터 조인이 되어서 시작된다.

    @EntityGraph(attributePaths = "member") // 처음부터 패치가 된다
    //바로 조회 할 엔티티 명시
        // Board Data의 member 엔티티, 배열 형태가 많으면 중괄호 형태로 입력
    List<BoardData> findBySubjectContaining(String key);
}
