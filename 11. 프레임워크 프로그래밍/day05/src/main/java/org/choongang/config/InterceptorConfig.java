package org.choongang.config;

import lombok.RequiredArgsConstructor;
import org.choongang.global.interceptors.MemberOnlyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final MemberOnlyInterceptor memberOnlyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) { // 매개변수인 registry 객체를 이용하여 인터셉터를 등록
        registry.addInterceptor(memberOnlyInterceptor)
                .addPathPatterns("/mypage/**"); // mypage 포함 하위 모든 경로도 포함
        //  /mypage/** 경로에 접근하는 모든 요청에 대해 MemberOnlyInterceptor 를 적용
    }
}
