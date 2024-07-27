package org.choongang.global.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동 생성
public class DBConfig {

    @PersistenceContext // 엔티티 매니저 주입
    private EntityManager em;

    @Lazy // 매번 처음부터 만들어지는 것이 아니라 필요할때 생성된다.
    @Bean // 스프링 관리 객체로 만들면 매번 조립할 필요 없이 사용할 수 있다!
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    } // JPAQueryFactory 빈을 생성하고 설정
}
