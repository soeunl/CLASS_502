package org.choongang.member.controllers;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestJoin { // 필수 항목 검증을 이렇게 하게 된다
    @NotBlank(message = "이메일을 입력하세요")
    @Email(message = "이메일 형식이 아닙니다")
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요")
    @Size(min=8)
    private String password;

    @NotBlank(message = "비밀번호를 확인하세요")
    private String confirmPassword;

    @NotBlank(message = "이름을 입력하세요")
    private String userName;

    @AssertTrue(message = "동의한다고 하세요")
    private boolean agree;
}


/*
public class RequestJoin {
    private String email;
    private String password;
    private String confirmPassword;
    private String userName;
    // private String[] hobby;
    // private Set<String> hobby;
    // private List<String> hobby;
    private String hobby;
    private boolean agree;
    private Address addr;
}
*/
