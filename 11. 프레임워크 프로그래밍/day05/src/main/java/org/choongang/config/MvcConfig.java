package org.choongang.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.RequiredArgsConstructor;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("org.choongang") // org.choongang은 모두 자동 스캔
@Import({DBConfig.class, MessageConfig.class, InterceptorConfig.class, FileConfig.class})
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
    // configurer.enable(); 로 기본 서블릿 처리를 활성화
    // 정적 리소스 요청을 처리하기 위해 기본 서블릿을 사용

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**") // 1순위는 컨트롤러 빈
                .addResourceLocations("classpath:/static/"); // 2순위로 접속될 경로 (정적 경로)
    }

//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.jsp("/WEB-INF/templates/", ".jsp");
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 간단한 페이지만 연결 설정
        registry.addViewController("/").setViewName("main/index"); // 컨트롤러를 따로 설정하지 않고 뷰만 연결

        registry.addViewController("/mypage")
                .setViewName("mypage/index");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer () {
        // 시스템 환경 변수에 따라 활성화된 프로파일을 고려하여 프로퍼티 파일을 로드함
        //로드된 프로퍼티 값들은 스프링 애플리케이션에서 @Value 어노테이션을 사용하여 주입할 수 있음

        String fileName = "application"; // 프로퍼티 파일의 기본 이름을 "application"으로 설정
        String profile = System.getenv("spring.profiles.active"); // 시스템 환경 변수 값을 가져와 profile 변수에 저장
        fileName += StringUtils.hasText(profile) ? "-" + profile: ""; // 활성화된 프로파일이 존재한다면 (StringUtils.hasText(profile)) 파일 이름 뒤에 하이픈(-)과 함께 프로파일 이름을 추가
        // 예를 들어, 활성화된 프로파일이 "dev"라면 파일 이름은 "application-dev.properties"가 될 것

        /**
         * spring.profiles.active=dev
         * -> application-dev
         *
         * spring.profiles.active=prod
         * -> application-prod
         */

        PropertySourcesPlaceholderConfigurer conf = new PropertySourcesPlaceholderConfigurer();
        // 프로퍼티 파일의 값을 Spring 애플리케이션에서 사용할 수 있도록 처리

        conf.setLocations(new ClassPathResource(fileName + ".properties"));
        // 기본적으로 Class class에 있는 경로 인식
        // 위에서 설정한 fileName 변수에 ".properties" 확장자를 붙여 파일 경로를 완성
        return conf;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) { // 응답 JSON 데이터에서 날짜와 시간이 원하는 포맷으로 표현되도록 함
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 날짜와 시간을 원하는 포맷("yyyy-MM-dd HH:mm:ss")으로 변환하는 포맷 객체를 생성
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
                .json() // 응답하는 형식을 설정 (.xml로도 설정할 수 있다). 여기서는 JSON 응답 형식을 설정
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(formatter)).build();
                // 위에서 생성한 formatter 객체를 사용하여 날짜와 시간을 원하는 포맷으로 변환

        converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
    }
}
