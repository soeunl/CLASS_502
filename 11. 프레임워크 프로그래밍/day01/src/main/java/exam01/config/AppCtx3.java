package exam01.config;

import exam01.member.dao.MemberDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정 클래스임을 알려줌
// @ComponentScan(basePackages = "exam01.member") // member 패키지의 하위 패키지 모두 포함
@ComponentScan("exam01.member")
public class AppCtx3 {

//    @Bean
//    public MemberDao memberDao() {
//        System.out.println("수동 등록 빈");
//        return new MemberDao();
//    }
//
//    @Bean
//    public MemberDao memberDao2() {
//        System.out.println("수동 등록 빈");
//        return new MemberDao();
//    }
}
