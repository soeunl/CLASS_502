package configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 설정클래스 입니다
@EnableWebMvc
@ComponentScan("member")
@Import(DBConfig.class)
public class MvcConfig implements WebMvcConfigurer { // 여기서는 웹에 대한 설정만 한다
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable(); // 찾는 자원이 없으면 정적 경로로 찾아서 들어간다
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // 뷰에 대한 경로를 주로 설정
        // 뷰처럼 환경에 따라 바뀔 수 있는 부분들은 직접 설정을 해야 한다!
        // 기본 세팅을 여기서 설정

        registry.jsp("/WEB-INF/templates/", ".jsp");
        // 반환값이 suffix에 들어간다
        
    }
}
