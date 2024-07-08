package exam01;

import exam01.config.AppCtx;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex01 {
    
    @Test 
    void test1() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class); // 스프링 컨테이너 객체
        // AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("exam01.config"); -> 패키지도 가능
        // AnnotationConfig - 설정방식
        // ApplicationContext - 스프링 컨테이너
        // AnnotationConfigApplicationContext - 설정클래스를 보고 관리할 객체를 찾아서 이름과 그 객체를 담는 컨테이너(그릇)
        // 설정 클래스의 정보 확인이 필요하기 때문에 Class class를 매개변수로 넣는다

        Greeter g1 = ctx.getBean("greeter", Greeter.class);
        // getBean : 꺼내올 때 쓰는 메서드
        g1.hello("이이름");

        Greeter g2 = ctx.getBean("greeter", Greeter.class);
        // getBean : 꺼내올 때 쓰는 메서드
        g1.hello("김이름");

        System.out.println(g1 == g2); // 주소 비교 - 싱글톤 형태로 관리
        
        ctx.close(); // 다 사용한 뒤 제거(소멸)
    }
    
    @Test
    void test2() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        // 싱글톤으로 기본 관리하므로 객체가 한개만 있는 경우가 많다
        // 그러므로 이름 없디 Class 클래스만 있어도 찾을 수 있다
        Greeter g1 = ctx.getBean("greeter", Greeter.class);
        g1.hello("이이름");
        
        ctx.close();
    }
}
