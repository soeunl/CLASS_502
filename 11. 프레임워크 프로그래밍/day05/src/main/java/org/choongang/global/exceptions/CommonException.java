package org.choongang.global.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CommonException extends RuntimeException {

    private HttpStatus status; // 응답코드를 스프링이 제공하는(지원하는) 이넘상수를 사용하겠다

    private Map<String, List<String>> errorMessages; // Rest일때 에러가 발생했을 때 메세지를 담아줄 용도이다
    // 커맨드 객체 검증을 위해 적었다
    // 출력을 통일성 있게 하기 위해!?

    public CommonException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR); // 500
        // 응답 코드가 없는 경우 500으로 고정하겠다
    }
    
    public CommonException(String message, HttpStatus status) {
        // 응답 코드를 받아서 만든다
        super(message);
        this.status = status;
    }
}
