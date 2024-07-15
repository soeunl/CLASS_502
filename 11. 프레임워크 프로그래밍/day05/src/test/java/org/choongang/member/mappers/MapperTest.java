package org.choongang.member.mappers;

import org.choongang.config.MvcConfig;
import org.choongang.member.entities.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringJUnitWebConfig // 다음 두 가지 애노테이션인 @ExtendWith와 @WebAppConfiguration을 결합한 것으로, 테스트 클래스가 Spring 테스트 컨텍스트와 통합될 수 있도록 확장(Extend)하며 테스트 환경이 웹 애플리케이션 컨텍스트임을 설정할 수 있다
@ContextConfiguration(classes = MvcConfig.class) // 해당 테스트 클래스가 실행될 때 필요한 Spring ApplicationContext(테스트 컨텍스트)를 구성하는 데 사용됨. MvcConfig.class에 정의된 설정 클래스를 사용하여 ApplicationContext를 구성하고 테스트를 실행할 수 있게됨
public class MapperTest {

    @Autowired
    private MemberMapper mapper;

    @Test
    void test1() {
        long total = mapper.getTotal();
        System.out.println(total);
    }
    @Test
    void test2() {
        Member member = mapper.get("user02@test.org");
        System.out.println(member);
    }

    @Test
    void test3() {
        int cnt = mapper.exists("user02@test.org");
        System.out.println(cnt);
    }
}
