package org.choongang.survey.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/survey")
@SessionAttributes("requestSurvey") // "requestSurvey"라는 세션 속성을 유지 관리할 것이다
public class SurveyController {

    @ModelAttribute
    public RequestSurvey requestSurvey() {
        return new RequestSurvey(); // 컨트롤러 내에서 유지되는 공통 데이터
    }
    // RequestSurvey 객체를 생성하여 requestSurvey라는 모델 속성에 담아 반환
    // 설문 조사 내용을 담는 모델 클래스

//    @GetMapping("/step1")
//    public String step1(@ModelAttribute RequestSurvey form) {
// 넘어 오는 값을 가지고 유지하기 위해 @ModelAttribute를 GET 방식에 사용
//        return "survey/step1";
//    }

    @GetMapping("/step1")  
    public String step1() { // @ModelAttribute를 따로 빼서 작성해서 (@ModelAttribute RequestSurvey form)를 제거하였음
        return "survey/step1";
    }

    @PostMapping("/step2")
    public String step2(RequestSurvey form, @SessionAttribute("requestSurvey") RequestSurvey form2) {
        // form: 사용자가 입력한 설문 내용, @SessionAttribute("requestSurvey") RequestSurvey form2: 세션에 저장된 "requestSurvey" 속성 값
        // 커맨드 객체 연동
        log.info("form : " + form.toString()); // model쪽에 추가되어 있는 RequestSurvey form 커맨드 객체
        log.info("form2 : " + form2.toString()); // 세션쪽에 유지되고 있으며 커맨드 객체 쪽에 자동 주입
        // 값을 유지해야 하기 때문에 값을 세션에 넣어주고, 그 값을 커맨드 객체에 넣어서 동기화

        return "survey/step2";
    }

    @PostMapping("/step3")
    public String step3(RequestSurvey form, @SessionAttribute("requestSurvey") RequestSurvey form2, SessionStatus status, HttpServletRequest request) {
        // 커맨드 객체 연동
        log.info("form : " + form.toString());
        log.info("form2 : " + form2.toString());

        status.setComplete(); // 세션 비우기 - requestSurvey 세션 비우기

        System.out.println("세션 비우기 후 : " + request.getSession().getAttribute("requestSurvey"));

        return "survey/step3";
    }
}
