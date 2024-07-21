package org.choongang.global.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class MemberOnlyInterceptor implements HandlerInterceptor {

    // 회원만 접근 가능한 페이지를 구현
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("preHandle()");

        HttpSession session = request.getSession(); // 사용자 세션 정보를 가져옴

        if (session.getAttribute("member") != null) { // 세션에 "member" 라는 속성값이 존재하는지 확인
            // 만약 세션에 "member" 속성값이 존재한다면 (사용자가 로그인한 상태)
            return true; // 컨트롤러 빈 메서드 실행
            // 사용자가 로그인한 상태이므로 회원 전용 페이지에 접근을 허용
        }

        response.sendRedirect(request.getContextPath() + "/member/login");
        // 만약 세션에 "member" 속성값이 없다면 (사용자가 로그인하지 않은 상태)
        // 로그인 페이지 주소로 리다이렉트 시킴
        return false;
        // 미로그인 상태
        // false로 컨트롤러 메서드 실행이 되지 않음
        // true, false로 통제 기능이 있다
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception { // 컨트롤러 메서드 실행 이후 뷰를 렌더링하기 전에 추가 작업 수행
        // // 시점이 메서드 실행 직후이기 때문에 ModelAndView가 매개변수로 들어가있다.
        // 컨트롤러에서 처리되지 못한 추가 데이터를 모델에 추가하여 뷰에 전달할 수 있다.
        // 뷰 이름이나 속성을 변경하여 뷰 렌더링 방식을 조정할 수 있다.
        // 모든 뷰에 공통적으로 적용될 메시지나 정보를 모델에 추가하여 전달할 수 있다.
        modelAndView.addObject("message", "Post Handle"); // modelAndView 객체에 "message" 라는 키로 "Post Handle" 이라는 값을 저장
        // addObject를 통해서 message를 modelAndView에 적용할 수 있다. message는 EL속성으로 접근이 가능하다.
        // 이는 컨트롤러에서 설정한 모델 데이터와 별도로 추가되는 데이터이며, 뷰 템플릿에서 EL 표현식을 사용하여 "message" 값을 참조하여 사용할 수 있다.
        log.info("postHandle()");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception { // 요청 처리 과정이 완전히 종료된 후 호출되는 인터셉터 메서드
        // 요청 처리 과정의 가장 마지막 단계에서 호출되며, 이후에는 더 이상 로직이 실행되지 않음
        log.info("afterCompletion()");
    }
}
