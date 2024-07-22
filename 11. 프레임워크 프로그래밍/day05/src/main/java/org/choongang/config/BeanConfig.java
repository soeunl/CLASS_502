package org.choongang.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ObjectMapper objectMapper() { // ObjectMapper = Java 객체를 JSON으로 변환하거나 JSON을 Java 객체로 변환하는 데 사용
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule()); // 기본 ObjectMapper 동작이 Java 8 시간 클래스(예: LocalDate, LocalDateTime 등)를 올바르게 처리하지 않을 수 있기 때문에 필요

        return om;
    }
}