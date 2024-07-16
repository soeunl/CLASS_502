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
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("preHandle()");

        HttpSession session = request.getSession();
        if (session.getAttribute("member") == null) {
            // 로그인 상태가 아닌 경우
            return true; // 컨트롤러 빈 메서드 실행
        }

        response.sendRedirect(request.getContextPath() + "/member/login");

        return false;
        // 미로그인 상태
        // false 컨트롤러 메서드 실행이 되지 않음
        // true, false로 통제 기능이 있다.
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("message", "Post Handle");
        // addObject를 통해서 message를 modelAndView를 적용할 수 있다. message는 EL속성으로 접근이 가능하다.
        log.info("postHandle()"); // 시점이 메서드 실행 직후이기 때문에 ModelAndView가 매개변수로 들어가있다.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion()");
    }
}
