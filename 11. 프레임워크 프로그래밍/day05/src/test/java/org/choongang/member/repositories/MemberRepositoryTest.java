package org.choongang.member.repositories;

import org.choongang.config.MvcConfig;
import org.choongang.member.entities.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.asc;
import static org.springframework.data.domain.Sort.Order.desc;

@SpringJUnitWebConfig
// 다음 두 가지 애노테이션인 @ExtendWith와 @WebAppConfiguration을 결합한 것으로, 테스트 클래스가 Spring 테스트 컨텍스트와 통합될 수 있도록 확장(Extend)하며 테스트 환경이 웹 애플리케이션 컨텍스트임을 설정할 수 있다
@ContextConfiguration(classes = MvcConfig.class) // 해당 테스트 클래스가 실행될 때 필요한 Spring ApplicationContext(테스트 컨텍스트)를 구성하는 데 사용됨. MvcConfig.class에 정의된 설정 클래스를 사용하여 ApplicationContext를 구성하고 테스트를 실행할 수 있게됨

public class MemberRepositoryTest {

    @Autowired
    private MemberRepository repository;

    @Test
    void test1() {
        List<Member> members = (List<Member>) repository.findAll();
        members.forEach(System.out::println);
    }

    @Test
    void test2() {
        Member member = Member.builder()
                .seq(1L)
                .email("user01@test.org")
                .password("12345678")
                .userName("사용자06(수정)")
                .build();

        repository.save(member);
    }

    @Test
    void test3() {
        Member member = repository.findById(1L).orElse(null); // Optional 형태로 반환값이 나온다
        System.out.println(member);

        repository.delete(member);
    }

    @Test
    void test4() {
        Member member = repository.findByEmail("user02@test.org");
        System.out.println(member);
    }

    @Test
    void test5() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Member> members = repository.findByUserNameContaining("용자", pageable);
        // members.forEach(System.out::println);
    }

    @Test
    void test6() {
        List<Member> members = repository.findByUserNameContainingAndEmailContainingOrderByRegDtDesc("용자", "user");
        members.forEach(System.out::println);
    }

    @Test
    void test7() {
        List<Member> members = repository.getMembers("%용자%", "%user%");
        members.forEach(System.out::println);
    }

    @Test
    void test8() {

        // Pageable pageable = PageRequest.of(0, 3);
        Pageable pageable = PageRequest.of(0, 3, Sort.by(desc("regDt"), asc("email")));

        Page<Member> data = repository.findByUserNameContaining("용자", pageable);

        List<Member> members = data.getContent();

        long total = data.getTotalElements(); // 조회된 전체 레코드 갯수
        int pages = data.getTotalPages();

        members.forEach(System.out::println);
    }
}
