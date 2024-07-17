package org.choongang.member.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.choongang.global.exceptions.BadRequestException;
import org.choongang.member.entities.Member;
import org.choongang.member.mappers.MemberMapper;
import org.choongang.member.services.JoinService;
import org.choongang.member.services.LoginService;
import org.choongang.member.validators.JoinValidator;
import org.choongang.member.validators.LoginValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
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
    public String login(@ModelAttribute RequestLogin form,
                        @CookieValue(name="savedEmail", required = false) String savedEmail/*, // 실제 쿠키 이름, 주입될 변수명
                        @SessionAttribute(name="member", required = false) Member member */) { // 세션 방법
        /*
        if (member != null) {
            log.info(member.toString());
        }
        */

        if (savedEmail != null) {
            form.setSaveEmail(true);
            form.setEmail(savedEmail);
        }

        return "member/login";
    }

    @PostMapping("/login")
    public String loginPs(@Valid RequestLogin form, Errors errors) // 1차적 검증
    { 
        loginValidator.validate(form, errors); // 2차적 검증

        if (errors.hasErrors()) { // 검증 실패시 참이 됨
            return "member/login";
        }

        loginService.process(form); // 로그인 처리

        return "redirect:/";
    }
    
    @RequestMapping("/logout") // GET이든 POST이든 상관 없다
    public String logout(HttpSession session) { // 로그아웃은 세션을 비우는 것이다
        session.invalidate(); // 세션 비우기

        return "redirect:/member/login";
    }

    @GetMapping("/list")
    public String list(@Valid @ModelAttribute MemberSearch search, Errors errors) {

        log.info(search.toString());

        boolean result = false;
        if(!result) {
            throw new BadRequestException("예외가 발생!!!");
        }

        return "member/list";
    }

    @ResponseBody
    @GetMapping("/info/{id}/{id2}")
    public void info(@PathVariable("id") String email, @PathVariable(name="id2", required = false) String email2) {
        log.info("email:{}, email2:{}", email, email2);
    }

    @ResponseBody
    @GetMapping("/list2")
    public List<Member> list() {
        List<Member> members = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> Member.builder()
                        .email("user" + i + "@test.org")
                        .password("12345678")
                        .userName("사용자" + i)
                        .regDt(LocalDateTime.now())
                        .build())
                .toList();

        return members;
    }


    @ExceptionHandler(Exception.class) // 다형성 활용
    public String errorHandler(Exception e, HttpServletRequest request, HttpServletResponse response, Model model) { 
        // 다형성으로 모든 예외가 여기로 유입된다.

        e.printStackTrace();
        log.info("MemberController에서 유입!");
        return "error/common";
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.setValidator(joinValidator);
//    }

    // @ModelAttribute는 요청 데이터를 객체로 변환하고, 필요한 경우 모델에 추가하는 데 사용
    // @GetMapping과 함께 @ModelAttribute를 사용하면, GET 요청 시 쿼리 파라미터를 객체로 변환하고,
    // 이를 모델에 추가하여 뷰에서 쉽게 활용할 수 있도록 도와줌
}
