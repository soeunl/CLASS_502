package org.choongang.global.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class BadRequestException extends CommonException {
    public BadRequestException(String message) {
        
        super(message, HttpStatus.BAD_REQUEST); // 400대로 나타내겠다
    }

    public BadRequestException(Map<String, List<String>> errorMessages) { // 커맨드 객체 검증시에 발생하는 메세지를 생성자 매개변수로 넣었다
        // 맵의 키는 에러 필드명, 값은 해당 필드에 대한 에러 메시지 목록이다
    super(null, HttpStatus.BAD_REQUEST);
    setErrorMessages(errorMessages); // 출력할 때 활용하기 위해 담아주었다
    }
}
