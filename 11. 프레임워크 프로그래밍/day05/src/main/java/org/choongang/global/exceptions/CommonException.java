package org.choongang.global.exceptions;

import org.springframework.http.HttpStatus;

public class CommonException extends RuntimeException {

    private HttpStatus status; // 응답코드를 스프링이 제공하는(지원하는) 이넘상수를 사용하겠다

    public CommonException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR); // 500
        // 응답 코드가 없는 경우 500으로 고정하겠다
    }
    
    public CommonException(String message, HttpStatus status) {

        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
