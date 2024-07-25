package org.choongang.jpa_study;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.choongang.board.entities.BoardData;
import org.choongang.board.entities.QBoardData;
import org.choongang.board.repositories.BoardDataRepository;
import org.choongang.member.constants.Authority;
import org.choongang.member.entities.Member;
import org.choongang.member.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class Ex12 {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardDataRepository boardDataRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @PersistenceContext // 엔티티 매니저 주입
    private EntityManager em;

    @BeforeEach
    void init() {
        Member member = Member.builder()
                .email("user01@test.org")
                .password("12345678")
                .userName("사용자01")
                .authority(Authority.USER)
                .build();

        memberRepository.saveAndFlush(member);

        // 회원을 꼭 포함해야지 외래키로 회원 번호가 들어간다
        List<BoardData> items = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> BoardData.builder()
                        .subject("제목" + i)
                        .content("내용" + i)
                        .member(member) // 꼭 넣어야지 추가된 회원번호로 외래키에 들어간다
                        .build()).toList();

        boardDataRepository.saveAllAndFlush(items);
        em.clear(); // JPA 영속성 콘텍스트는 1차 캐시의 역할도 한다.
        // 1차 캐시 때문에 쿼리 실행이 보이지 않기 때문에 우리는 공부를 위해 일부러 비운다.
        // 쿼리 실행 되는거 볼려고!
    }

    @Test
    void test1() {
        List<BoardData> items = boardDataRepository.findAll();

        for(BoardData item : items) {
            Member member = item.getMember();
            String email = member.getEmail();
            String userName = member.getUserName();
            System.out.printf("email=%s, userName=%s\n", email, userName);
        }
    }
    // 글로벌 전략으로 지연로딩을 쓰지만, 필요할때는 선별적으로 할 수 있다.
    // 지연 로딩이 리스트를 조회할 때는 쿼리가 너무 많이 수행 된다는 단점이 있다.
    // 목록 갯수만큼 쿼리가 수행된다.
    // 항목의 갯수만큼 쿼리가 배로 늘어나고 성능이 떨어진다.
    // 이것을 N+1 문제라고 한다.
    // 이것의 해결방법이 바로 Fetch 조인이다. -> 필요한 엔티티만 즉시 로딩 전략을 사용!

    @Test
    void test2() {
        List<BoardData> items = boardDataRepository.getAllList();
    }

    @Test
    void test3() {
        List<BoardData> items = boardDataRepository.findBySubjectContaining("제목");
    }

    @Test
    void test4() {
        QBoardData boardData = QBoardData.boardData;

        // JPAQueryFactory factory = new JPAQueryFactory(em); // 빈으로 등록하면 더 편할 것이다.
        JPAQuery<BoardData> query = jpaQueryFactory
                .selectFrom(boardData)
                .leftJoin(boardData.member) // boardData안에 있는 member라는 뜻이다.
                .fetchJoin();

        List<BoardData> items = query.fetch();
        items.forEach(System.out::println);

        // QueryDsl은 Q클래스를 씁니다.
        // 본인이 만든 큐형태의 클래스만 쓴다.
    }

    @Test
    void test5() {
        QBoardData boardData = QBoardData.boardData;
        JPAQuery<Tuple> query = jpaQueryFactory.select(boardData.subject, boardData.content)
                .from(boardData);
        List<Tuple> items = query.fetch();
        // 필드가 두개 이상일 때 튜플로 가지고 오고 형변환을 통해 사용한다.
        for (Tuple item : items) {
            String subject = item.get(boardData.subject);
            String content = item.get(boardData.content);
            System.out.printf("subject=%s, content=%s\n", subject, content);
        }
    }

    @Test
    void test6() {
        QBoardData boardData = QBoardData.boardData;
        JPAQuery<Long> query = jpaQueryFactory.select(boardData.seq.sum())
                .from(boardData);
    }
}
