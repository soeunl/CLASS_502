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
    // 스프링 데이터 JPA는 레포지토리 인터페이스를 보고 동적으로 프록시 객체를 생성함. 이 프록시 객체는 실제 데이터베이스와 상호작용하는 역할을 하며, 메소드 이름을 기반으로 SQL 쿼리를 생성함
    // JPA 레포지토리 인터페이스를 상속함으로써 기본적인 CRUD(Create, Read, Update, Delete) 기능을 제공
    // <BoardData, Long>은 이 레포지토리가 BoardData 엔티티를 관리하며, 기본 키(Primary Key)의 타입이 Long임을 나타냄
    // QuerydslPredicateExecutor 인터페이스를 상속함으로써 Querydsl 라이브러리를 활용하여 동적인 쿼리 작성 가능

    // JPQL
    @Query("SELECT b FROM BoardData b LEFT JOIN FETCH b.member")
    List<BoardData> getAllList();
    // 이렇게 해두어도 엔티티에 정의한 매핑 전략으로 쿼리가 실행된다,,,?
    // 기본적으로 엔티티에 정의된 전략대로 쿼리가 수행된다.
    // FETCH를 쓰면 처음부터 조인이 되어서 시작된다.

    @EntityGraph(attributePaths = "member") // 처음부터 패치가 된다
    //바로 조회 할 엔티티 명시
        // Board Data의 member 엔티티, 배열 형태가 많으면 중괄호 형태로 입력
    // 쿼리 실행 시 member 필드와 연관된 엔티티(게시글 작성자 정보)를 함께 가져오도록 지시
    List<BoardData> findBySubjectContaining(String key);
    // 메서드 매개변수로 String key를 받아 제목(subject)에 key 문자열이 포함된 게시글 목록을 조회
}
