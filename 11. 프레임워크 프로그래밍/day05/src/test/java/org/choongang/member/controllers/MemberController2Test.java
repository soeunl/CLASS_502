package org.choongang.member.controllers;

import org.choongang.config.MvcConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringJUnitWebConfig
@ContextConfiguration(classes = MvcConfig.class)
public class MemberController2Test {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    void test1() throws Exception {
        mockMvc.perform(post("/member/join")
                        .header("appKey", "1234") // 요청 헤더
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                 )
                .andDo(print()); // 자세한 정보가 나옴
        // 요청에 대한 모든 자세한 정보나 스프링프레임워크 내에서 찾은 컨트롤러 등 기타 정보들이 모두 나온다
    }

    @Test
    void test2() throws Exception {
        mockMvc.perform(get("/member/join")
                        .header("Accept-Language", Locale.KOREAN.getDisplayLanguage()))
                .andDo(print());
    }
}
