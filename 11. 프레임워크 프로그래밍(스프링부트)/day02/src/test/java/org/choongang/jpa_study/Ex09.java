package org.choongang.jpa_study;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.choongang.board.entities.BoardData;
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
// 원 테이블 데이터가 계속해서 영속성 상태를 유지하기 위해 사용
// 관계 매핑 / 지연로딩 시 데이터를 가지고 오지 못하는 경우가 있으므로!
public class Ex09 {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardDataRepository boardDataRepository;

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
    void test1() { // @ManyToOne 테스트
        BoardData item = boardDataRepository.findById(1L).orElse(null); // 게시글 조회
        // System.out.println(item); // 게시글 데이터 출력
        Member member = item.getMember(); // 게시글을 작성한 회원
        System.out.println(member);
    }

    @Test
    void test2() { // @OneToMany 테스트
        Member member = memberRepository.findById(1L).orElse(null); // 회원 조회
        List<BoardData> items = member.getItems(); // 회원의 보드 데이터 리스트
        items.forEach(System.out::println);
    }
}
