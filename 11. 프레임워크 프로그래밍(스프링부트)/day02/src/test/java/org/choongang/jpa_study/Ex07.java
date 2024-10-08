package org.choongang.jpa_study;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.choongang.member.constants.Authority;
import org.choongang.member.entities.Member;
import org.choongang.member.entities.QMember;
import org.choongang.member.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
public class Ex07 {

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void init() {

        List<Member> members = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> Member.builder()
                        .email("user" + i + "@test.org")
                        .password("12345678")
                        .userName("사용자" + i)
                        .authority(i % 2 == 0 ? Authority.USER : Authority.ADMIN)
                        .build()).toList();

        memberRepository.saveAllAndFlush(members); // 10명의 회원을 만들었음
    }

    @Test
    void test1() {
        QMember member = QMember.member;
        BooleanExpression c1 = member.userName.contains("용");

        List<Member> members = (List<Member>) memberRepository.findAll(c1);

        members.forEach(System.out::println);
    }
    
    @Test
    void test2() { // 다양한 조건을 다양한 방법으로 처리 가능하다
        String key = "용";
        QMember member = QMember.member;
        BooleanBuilder andBuilder = new BooleanBuilder(); // Predicate 조건식

        BooleanBuilder orBuilder = new BooleanBuilder();
        orBuilder.or(member.email.contains(key))
                .or(member.userName.contains(key)); // 주로 or조건식은 소괄호로 묶는다.
        // 여기서 or 조건은 and 조건에 항상 같이 묶어야 한다.
        
        andBuilder.and(orBuilder);

        List<Member> members = (List<Member>) memberRepository.findAll(andBuilder);
        members.forEach(System.out::println);
    }

    @Test
    void test3() {
        QMember member = QMember.member;
        BooleanExpression c1 = member.email.concat(member.userName).contains("용");

        List<Member> members = (List<Member>)memberRepository.findAll(c1);
        members.forEach(System.out::println);
    }
}
