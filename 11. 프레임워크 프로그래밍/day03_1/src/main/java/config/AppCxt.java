package config;

import exam01.Calculator;
import exam01.CalculatorHandler;
import exam01.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
// AOP 자동 설정 애노테이션
// @EnableAspectJAutoProxy는 스프링에서 AOP를 활성화하는 어노테이션으로, AspectJ 프록시를 사용하여 Aspect를 적용할 수 있게 해줌
public class AppCxt {

    @Bean // 여러 개의 같은 캐시에 적용이 가능하다
    public ProxyCache proxyCache() {
        return new ProxyCache();
    }

    @Bean
    public ProxyCalculator2 proxyCalculator() {
        return new ProxyCalculator2();
    }

    @Bean
    public Calculator calculator() {
        return new RecCalculator();
    }
}
