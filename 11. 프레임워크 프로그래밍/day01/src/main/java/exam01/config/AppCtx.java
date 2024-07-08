package exam01.config;

import exam01.Greeter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

// 설정 클래스 - 스프링 컨테이너가 관리할 객체를 정의하고, 설정한다
// 설정클래스는 여러개가 될수도 있고, 패키지로 설정할수도 있다
// @Import 하나의 설정 클래스에서 다른 설정 클래스를 포함시킬 수도 있다
// @Import(value={AppCtx2.class})
// value=을 빼고도 사용 가능 -> @Import({AppCtx2.class})
// value=에는 제일 중요한 속성이 들어간다. 기본적인 속성. 제일 많이 쓰는걸 넣는다
// 여기서는 포함 시킬 설정 클래스의 정보가 가장 중요한 속성이다
// 속성이 하나만 있을때는 value=를 쓰지 않아도 자동으로 value=로 인식한다
// 여러개 있을 때는 value=를 써야한다
// 하나만 있을 때는 {}도 생략이 가능하다

@Import({AppCtx2.class})
@Configuration
public class AppCtx {

    // @Bean이 있으면 관리할 객체임을 알고 컨테이너에 담는다
    @Bean
    public Greeter greeter() { // gretter가 객체의 이름이 된다
        return new Greeter();
    }

    // @Bean이 없으면 이 안에서만 쓸 목적
}
