package exam01.config;

import exam01.member.dao.MemberDao;
import exam01.member.services.InfoService;
import exam01.member.services.JoinService;
import exam01.member.validators.JoinValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
// 스프링이 관리하는 설정 클래스입니다~~
public class AppCtx2 {
    // 의존성을 주입하는 조립기의 역할을 대신할 수 있다

    @Bean
    // 스프링이 관리하는 객체입니다~~
    @Qualifier("mDao") // 짧게 쓰는 이름으로도 설정할 수 있다
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    // 스프링이 관리하는 객체입니다~~
    public MemberDao memberDao2() {
        return new MemberDao();
    }

    @Bean
    // 스프링이 관리하는 객체입니다~~
    public JoinValidator joinValidator() {
        JoinValidator joinValidator = new JoinValidator();
        // joinValidator.setMemberDao(memberDao());
        // 의존성 주입이 되지 않아서 오류가 발생한다
        // JoinValidator의 setter메서드 위에 @Autowired을 붙이면 자동 주입이 된다

        return joinValidator;
    }

    @Bean
    // 스프링이 관리하는 객체입니다~~
    public JoinService joinService() {
        //return new JoinService(joinValidator(), memberDao());
        // 의존성 주입이 되지 않아서 오류가 발생한다
        // JoinValidator의 setter메서드 위에 @Autowired을 붙이면 자동 주입이 된다

        return new JoinService();
    }

    @Bean
    // 스프링이 관리하는 객체입니다~~
    public InfoService infoService() {
        InfoService infoService = new InfoService();
        // infoService.setMemberDao(memberDao());
        // 의존성 주입이 되지 않아서 오류가 발생한다
        // JoinValidator의 setter메서드 위에 @Autowired을 붙이면 자동 주입이 된다

        return infoService;
    }
    @Bean // 자바 JDK, 외부 라이브러리는 수동 등록이 필수
    public DateTimeFormatter dateTimeFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

        return formatter;
    }
}
