package org.choongang.global;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Utils { // Rest 형태 일때는 에러를 직접 가공해야 한다

    private final MessageSource messageSource; // getMessage 메서드 사용을 위해
    private final HttpServletRequest request; // 요청 쪽에 있는 브라우저 언어를 위해 사용. Locale 설정을 위해서!

    public Map<String, List<String>> getErrorMessage(Errors errors) { // 필드별로 있는 오류 메세지이므로 Map 형태로 가공하였다
        // FieldErrors 처리
        Map<String, List<String>> messages = errors.getFieldErrors() // 에러 코드의 정보가 담긴 에러를 가지고 온다
                .stream() // 스트림으로 만들고
                .collect(Collectors.toMap(FieldError::getField, e -> getCodeMessages(e.getCodes()))); // 필드명이 키가 되고, 발생한 에러가 리스트 형태로 값에 들어간다

        // 모아서 맵 형태로 만든다
        // 키값이 필드명, 값에는 에러 메세지를 리스트 형태로 만들어 주었다
        
        //GlobalErrors 처리
        List<String> gMessages = errors.getGlobalErrors()
                .stream()
                .flatMap(e -> getCodeMessages(e.getCodes()).stream()).toList();
        if (!gMessages.isEmpty()) {
            messages.put("global", gMessages);
        }
        messages.put("global", gMessages);

        return messages;
    }

    public List<String> getCodeMessages(String[] codes) {
        
        ResourceBundleMessageSource ms = (ResourceBundleMessageSource) messageSource;
        
        ms.setUseCodeAsDefaultMessage(false);
        // 메세지가 없을 때 에러코드를 메세지로 대체하는걸 false로 설정함
        // 메세지를 추출할때만 false로 설정

        List<String> messages = Arrays.stream(codes)
                .map(c -> {
                    try { // 원래 비어 있으면 오류가 나기 때문에 예외 처리를 한다
                        return ms.getMessage(c, null, request.getLocale());
                    } catch (Exception e) {
                        return ""; // 없으면 비우고 List로 만든다?!
                        // 비어 있는 문자열로 반환
                    }
                })
//                .map(c -> messageSource.getMessage(c, null, request.getLocale()))
                .filter(s -> s != null && !s.isBlank()) // 없는 메세지는 나오지 않고 우리가 등록한 코드에 있는 메세지만 나온다
                .toList();

        ms.setUseCodeAsDefaultMessage(true); // 싱글톤이기 때문에 다시 원래 상태인 true로 바꿔 주어야 한다
        // 쓸때만 바꾸고 다 쓰면 돌려 주어야 한다
        return messages;
    }
}
