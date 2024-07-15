package org.choongang.member.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.member.services.JoinService;
import org.choongang.member.services.LoginService;
import org.choongang.member.validators.JoinValidator;
import org.choongang.member.validators.LoginValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member") // 공통적으로 앞에 추가될 주소
@RequiredArgsConstructor // 생성자 매개변수 주입
public class MemberController {

    private final JoinValidator joinValidator; // 의존성 주입
    private final JoinService joinService;

    private final LoginValidator loginValidator;
    private final LoginService loginService;


    @GetMapping("/join")
    public String join(@ModelAttribute RequestJoin form) {
        return "member/join";
    }

    @PostMapping("/join")
    public String joinPs(@Valid RequestJoin form, Errors errors)
            // 커맨드 객체. EL식 속성으로 넘어온다.
            // RequestJoin form 이 데이터를 검증하려고 하는 것이다.
            // 전역 Validator 할때는 @Valid가 꼭 있어야 한다.
     {
         joinValidator.validate(form, errors); // 회원가입 데이터 검증

         if (errors.hasErrors()) { // reject, rejectValue가 한번이라도 호출되면 true
             // 검증 실패 시 넘어가는 것이 아니라 다시 양식을 보여준다.
             // 이상이 없을 때만 아래쪽 return "redirect:/member/login"으로 넘어간다
             return "member/join";
         }
         
         joinService.process(form); // 회원 가입 처리
         
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute RequestLogin form) {
        return "member/login";
    }

    @PostMapping("/login")
    public String loginPs(@Valid RequestLogin form, Errors errors) // 1차적 검증
    { 
        loginValidator.validate(form, errors); // 2차적 검증

        if (errors.hasErrors()) { // 검증 실패시 참이 됨
            return "member/login";
        }

        String email = form.getEmail();
        loginService.process(email); // 로그인 처리

        return "redirect:/";
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.setValidator(joinValidator);
//    }
}
