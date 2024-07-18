package org.choongang.global.advice;

import org.choongang.global.exceptions.CommonException;
import org.choongang.global.rests.JSONData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;

@ControllerAdvice("org.choongang")
public class RestCommonControllerAdvice {
    @ExceptionHandler(Exception.class)
    
    public ResponseEntity<JSONData> errorHandler(Exception e) { // JSON 형태로 통일성 있게 처리하기 위해 형식을 고정!

        Object message = e.getMessage();

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
        if (e instanceof CommonException commonException) { // CommonException의 하위 클래스이면 응답코드를 가지고 와서 교체한다
            status = commonException.getStatus();

            Map<String, List<String>> errorMessages = commonException.getErrorMessages();
            if (errorMessages != null ) message = errorMessages;
        }

        JSONData data = new JSONData(); // 통일된 형식으로 JSON 형태로 출력한다
        data.setSuccess(false);
        data.setMessage(message);
        data.setStatus(status);

        e.printStackTrace();

        return ResponseEntity.status(status).body(data); // 응답 코드를 직접 설정할 수 있게 넣었다
    }
}
