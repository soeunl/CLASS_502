package org.choongang.jpa_study;

import org.choongang.member.constants.Authority;
import org.choongang.member.entities.Member;
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
public class Ex05 {

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void init() {

        List<Member> members = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> Member.builder()
                .email("user" + i + "@test.org")
                .password("12345678")
                .userName("사용자" + i)
                .authority(Authority.USER)
                .build()).toList();

        memberRepository.saveAllAndFlush(members);

//        for (int i = 1; i <= 10; i++) {
//        Member member = Member.builder()
//                .email("user" + i + "@test.org")
//                .password("12345678")
//                .userName("사용자" + i)
//                .authority(Authority.USER)
//                .build();
//
//        memberRepository.save(member); }

        // memberRepository.save(member); // 영속성 안에 있으면 반환값이 없어도 된다.
        // memberRepository.flush(); // db에 영구 반영

        // 한줄로 편하게 쓰자!
        // memberRepository.saveAndFlush(member);
        
        // member.setUserName("수정된 사용자"); // flush가 암묵적으로 일어난다
    }

    @Test
    void test1() {
        Member member = memberRepository.findById(1L).orElse(null);
        System.out.println(member);

        member.setUserName("수정된 사용자");
        memberRepository.save(member);

        Member member2 = memberRepository.findById(1L).orElse(null);
        System.out.println(member2);

        memberRepository.delete(member2);
        memberRepository.flush();
    }

    @Test
    void test2() {
        List<Member> members = memberRepository.findAll();
        members.forEach(System.out::println);
    }

}
