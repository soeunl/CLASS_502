package org.choongang.member.controllers;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/member2")
@RequiredArgsConstructor
public class MemberController2 {

    private final MessageSource messageSource;
    private final HttpServletRequest request;

    @ModelAttribute("commonValue")
    public String commonValue() {
        return "공통 속성값"; // 컨트롤러 내에서 유지되는 공통 데이터
    }

    @ModelAttribute("hobbies")
        public List<String> hobbies() {
            return List.of("취미1", "취미2", "취미3", "취미4");
        }

    @ModelAttribute("hobbies2")
    public List<CodeValue> hobbies2() {
        return List.of(
                new CodeValue("취미1", "hobby1"),
                new CodeValue("취미2", "hobby2"),
                new CodeValue("취미3", "hobby3"),
                new CodeValue("취미4", "hobby4")
        );
    }

    @GetMapping("/join")
    public String join(@ModelAttribute("command") RequestJoin form) {

        Locale locale = request.getLocale(); // 요청 헤더 Accept-Language
        String message = messageSource.getMessage("EMAIL", null, locale);
        log.info(message);
        return "member/join";
        // 커맨드 객체가 넘어오지 않을 때 속성명으로 바로 추가할수도 있음..?
    }

//    @GetMapping("/join")
//    public String join(Model model) {
//        RequestJoin form = new RequestJoin();
//        model.addAttribute("requestJoin", form);
//        // 모델을 설정해서 커맨드 객체의 값을 유지해야 한다
//        // 커맨드 객체는 요청에 따라 달라지며 새로 만드므로 관리객체가 되지 않는다
//        return "member/join";
//    }

    @PostMapping("/join")
    public String joinPs(@ModelAttribute("command") RequestJoin form) // 커맨드 객체
    {
        log.info(form.toString());
        return "member/join";
        // return "redirect:/member/login";
        // Location: /day05/member/login
        // 앞에 /가 있으면 절대경로, 없으면 상대경로
        // return "foward:/member/login"; // 버퍼의 치환
    }

    @GetMapping("/login")
    public String login(RequestLogin2 form) {

        if (form != null) {
            log.info("이메일:{}, 비밀번호:{}", form.email(), form.password());
        }
        return "member/login";
    }

//    private final Logger log = LoggerFactory.getLogger(MemberController.class);

    // @GetMapping("/member/join")
//    @RequestMapping(path="/member/join", method = {RequestMethod.GET, RequestMethod.POST })
//    public String join(Model model, HttpServletRequest request) {
//        model.addAttribute("message", "안녕하세요 가입입니다");
//        System.out.println("method : " + request.getMethod());
//        return "member/join";
//    }
//    @GetMapping("/join")
//    public String join1() {
//        log.info("{}, {} 없음", "mode1", "mode2");
//        return "member/join";
//    }
//
//    @GetMapping(path="/join", params = {"mode=join"}) // params는 데이터가 없으면 유입이 안된다!
//    public String join() {
//        log.info("mode=join");
//        return "member/join";
//    }
//
//    @PostMapping(value = "/join", headers = "appkey=1234", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public String joinPs(RequestJoin form) {
//        log.info("join 실행");
//        return "redirect:/member/login";
//    }

//    @GetMapping("/member/join")
//    public ModelAndView join() {
//
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("message", "안녕하세요");
//        mv.setViewName("member/join");
//
//        return mv;
//    }
}
