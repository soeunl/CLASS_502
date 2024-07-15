package org.choongang.member.controllers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestLogin {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private  boolean saveEmail;
}

// DTO는 사용자가 입력한 요청 데이터를 전달하는 목적
// DTO는 기본 데이터도 검증한다
// 여기는 커맨드 객체
// 데이터를 담는 곳이 VO
