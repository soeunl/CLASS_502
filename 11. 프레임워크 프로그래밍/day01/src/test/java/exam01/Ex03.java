package exam01;

import exam01.config.AppCtx;
import exam01.config.AppCtx2;
import exam01.member.controllers.RequestJoin;
import exam01.member.dao.MemberDao;
import exam01.member.services.InfoService;
import exam01.member.services.JoinService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex03 {

    @Test
    void test1() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx2.class);

        JoinService joinService = ctx.getBean(JoinService.class);
        InfoService infoService = ctx.getBean(InfoService.class);

        RequestJoin form = RequestJoin.builder()
                .email("user01@test.org")
                .userName("사용자01")
                .password("12345678")
                .confirmPassword("12345678")
                .build();

        joinService.process(form);

       infoService.printList();

        ctx.close();
    }

    @Test
    void test2() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class, AppCtx2.class);
        // 설정 클래스가 많을 때는 매개변수에 여러개를 적을 수도 있고, 패키지를 적을 수도 있다
        // AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("exam01.config"); -> 패키지도 가능

        JoinService joinService = ctx.getBean(JoinService.class); // AppCtx2
        InfoService infoService = ctx.getBean(InfoService.class); // AppCtx2

        RequestJoin form = RequestJoin.builder()
                .email("user01@test.org")
                .userName("사용자01")
                .password("12345678")
                .confirmPassword("12345678")
                .build();

        joinService.process(form);

        infoService.printList();

        Greeter g1 = ctx.getBean(Greeter.class); // AppCtx2
        g1.hello("이이름");

        ctx.close();
    }
}
