package org.choongang.config;

import lombok.RequiredArgsConstructor;
import org.choongang.member.validators.JoinValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@ComponentScan("org.choongang") // org.choongang은 모두 자동 스캔
@Import({DBConfig.class, MessageConfig.class, InterceptorConfig.class})
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    // private final JoinValidator joinValidator;

    // 모든 컨트롤러에 적용될 수 있는 전역 Validator
//    @Override
//    public Validator getValidator() {
//        return joinValidator;
//    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**") // 1순위는 컨트롤러 빈
                .addResourceLocations("classpath:/static/"); // 2순위로 접속될 경로 (정적 경로)
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/templates/", ".jsp");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 간단한 페이지만 연결 설정
        registry.addViewController("/").setViewName("main/index"); // 컨트롤러를 따로 설정하지 않고 뷰만 연결

        registry.addViewController("/mypage")
                .setViewName("mypage/index");
    }
}
