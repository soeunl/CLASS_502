package org.choongang.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer {

    @Value("${file.upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 파일 업로드 정적 경로 설정
        // 요청 URL 패턴과 해당 URL 패턴에 매핑되는 실제 리소스 위치를 지정
//        registry.addResourceHandler("/uploads/**").addResourceLocations("file:///D:uploads/"); // 슬래시 하나는 제거 되기 때문에 꼭 하나 더 추가 해서 넣어야 한다
        registry.addResourceHandler("/uploads/**") // /uploads/ 로 시작하는 모든 URL 요청을 처리함
                .addResourceLocations("file:///" + uploadPath); // 업로드된 파일들이 저장된 실제 리소스 위치를 지정
    }
}
