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
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/templates2/");
        templateResolver.setSuffix(".html"); // 다른 확장자도 가능
        templateResolver.setCacheable(false); // 캐시
        // false일때는 매번 번역을 한다 true일때는 한번 번역한 파일을 그대로 유지한다
        // 개발 할때는 false로 해서 해야한다
        // 매번 요청이 올 때마다 번역을 다시 한다
        // 개발 때는 false, 배포 때는 true로 바꾼다
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true); // EL식을 지원한다 (데이터를 넘기면 EL식 형태로 활용해야 하기 때문이다)
        templateEngine.addDialect(new Java8TimeDialect()); // 확장 기능 추가
        templateEngine.addDialect(new LayoutDialect()); // 공통적인 기능은 고정되어 있고 교체되는 부분만 교체된다
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setContentType("text/html");
        resolver.setCharacterEncoding("utf-8");
        resolver.setTemplateEngine(templateEngine());
        return resolver;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(thymeleafViewResolver()); // 템플릿을
    }
}
