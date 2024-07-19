package org.choongang.config;


import lombok.RequiredArgsConstructor;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@RequiredArgsConstructor
public class ThymeleafConfig implements WebMvcConfigurer {
    private final WebApplicationContext applicationContext;

    // Thymeleaf도 번역 기술이다

    @Bean
    public SpringResourceTemplateResolver templateResolver() { // 템플릿 경로에 대한 설정
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext); // Spring 애플리케이션 컨텍스트를 TemplateResolver에 설정
        // TemplateResolver는 등록된 리소스(예: 프로퍼티 파일, Bean)에 접근할 수 있게 됨
        templateResolver.setPrefix("/WEB-INF/templates2/"); // 템플릿 파일의 경로를 "/WEB-INF/templates2/"로 설정
        templateResolver.setSuffix(".html"); // 다른 확장자도 가능
        templateResolver.setCacheable(false); // 캐시
        // false일때는 매번 번역을 한다 true일때는 한번 번역한 파일을 그대로 유지한다
        // 개발 할때는 false로 해서 해야한다(변경 사항 반영을 위해)
        // 매번 요청이 올 때마다 번역을 다시 한다
        // 개발 때는 false, 배포 때는 true로 바꾼다
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() { // 템플릿 처리 및 렌더링을 담당
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver()); // 앞서 설정된 templateResolver 빈을 템플릿 엔진에 설정
        // templateResolver 빈은 템플릿 파일의 경로, 이름, 확장자 등을 결정하는 역할
        templateEngine.setEnableSpringELCompiler(true); // EL식을 지원한다 (데이터를 넘기면 EL식 형태로 활용해야 하기 때문이다)
        templateEngine.addDialect(new Java8TimeDialect()); // 확장 기능 추가. Java8 시간 API를 사용하는 템플릿 처리 기능을 추가
        templateEngine.addDialect(new LayoutDialect()); // 공통적인 기능은 고정되어 있고 교체되는 부분만 교체된다
        // Thymeleaf 레이아웃 기능을 사용하도록 설정
        // 템플릿 코드를 재사용하고, 공통적인 레이아웃을 유지하면서 동적인 콘텐츠를 삽입할 수 있게 됨
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() { // Thymeleaf 템플릿을 뷰(view)로 사용하도록 지원
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setContentType("text/html"); // 뷰(view)의 콘텐츠 타입을 "text/html"로 설정. 이는 렌더링된 결과가 HTML 페이지임을 나타냄
        resolver.setCharacterEncoding("utf-8");
        resolver.setTemplateEngine(templateEngine()); // ThymeleafViewResolver 는 템플릿 엔진을 사용하여 템플릿을 처리하고 렌더링할 수 있게 됨
        return resolver;
        // Thymeleaf 템플릿을 뷰(view)로 사용하도록 설정
        // 뷰의 콘텐츠 타입과 문자 인코딩을 설정하고, 앞서 설정한 템플릿 엔진을 연결하여 템플릿 처리를 위한 준비를 완료
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(thymeleafViewResolver());
        // Spring MVC 애플리케이션에서 Thymeleaf 템플릿 엔진을 사용하여 뷰를 처리하도록 설정
        // viewResolver를 등록함으로써 Spring MVC는 템플릿 이름을 실제 템플릿 객체로 변환하고, 템플릿 엔진을 사용하여 뷰를 렌더링하여 사용자에게 응답할 수 있게 됨
    }
}
