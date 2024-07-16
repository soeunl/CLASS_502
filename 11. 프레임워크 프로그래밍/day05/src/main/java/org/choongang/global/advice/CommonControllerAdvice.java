package org.choongang.global.advice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.choongang.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice("org.choongang") // 공통적인 값 유지, 공통적인 처리 등을 한다
public class CommonControllerAdvice { // 공통적인 처리를 위해 사용한다
    // @Controller의 기능에 덧붙여서 공통 기능을 하기 위해 존재한다
    // @Controller에 있는 우선 순위가 @ControllerAdvice보다 더 높다
    // @Controller > @ControllerAdvice
    // 똑같은 것이 정의 되어 있을 때 우선 순위가 더 높은 @Controller쪽으로 유입
    
    // 톰켓의 에러 페이지가 아닌 우리가 정의한 에러 페이지를 보여주기 위함
    @ExceptionHandler(Exception.class) // 다형성 활용
    public ModelAndView errorHandler(Exception e, HttpServletRequest request, HttpServletResponse response, Model model) { // Exception이기 때문에 온갖 예외가 다 유입된다.
        e.printStackTrace();
        log.info("advice 유입!");

        // 내가 정의한 예외는 정확한 응답 코드를 가지고 와야 한다. 일단 기본은 500
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 기본은 500
        
        if (e instanceof CommonException commonException) { // 출처를 먼저 확인
            // CommonException commonException = (CommonException) e; // 형변환
            // 출처가 맞으면 상태에 맞는 적절한 응답 코드를 가지고 온다
            status = commonException.getStatus(); // 형변환을 통해 에러코드를 가지고 오기 위함
        }

        ModelAndView mv = new ModelAndView();
        mv.setStatus(status); // 뷰와 응답코드를 같이 상세하게 설정

        mv.setViewName("error/common");
        return mv;
    }
}
