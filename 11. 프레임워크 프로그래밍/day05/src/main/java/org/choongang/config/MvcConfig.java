package org.choongang.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.*;

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

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer () {

        String fileName = "application";
        String profile = System.getenv("spring.profiles.active");
        fileName += StringUtils.hasText(profile) ? "-" + profile: "";

        /**
         * spring.profiles.active=dev
         * -> application-dev
         *
         * spring.profiles.active=prod
         * -> application-prod
         */

        PropertySourcesPlaceholderConfigurer conf = new PropertySourcesPlaceholderConfigurer();

        conf.setLocations(new ClassPathResource(fileName + ".properties"));
        // 기본적으로 Class class에 있는 경로 인식
        return conf;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
                .json() // 응답하는 형식을 설정 (.xml로도 설정할 수 있다)
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(formatter)).build();

        converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
    }
}
