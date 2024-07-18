package org.choongang.global.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class BadRequestException extends CommonException {
    public BadRequestException(String message) {
        
        super(message, HttpStatus.BAD_REQUEST); // 400대로 나타내겠다
    }

    public BadRequestException(Map<String, List<String>> errorMessages) {
    super(null, HttpStatus.BAD_REQUEST);
    setErrorMessages(errorMessages);
    }
}
