package org.choongang.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing // 엔티티의 생성일자(createdDate)와 수정일자(lastModifiedDate)를 자동으로 관리할 수 있도록 도와주는 기능을 활성화
@Configuration
@EnableScheduling // 스프링 스케줄링 기능을 활성화(정해진 시간이나 주기에 맞춰 특정 작업을 자동으로 실행하도록 하는 스프링 프레임워크의 기능)
public class MvcConfig implements WebMvcConfigurer { // @EnableWebMvc는 이제 없어도 된다!
}
