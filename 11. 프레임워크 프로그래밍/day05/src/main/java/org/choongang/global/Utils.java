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
    
    // 여기는 무엇을 하는 곳인가?
    // 컨트롤러에서 발생한 오류를 처리하여 클라이언트에게 적절한 형태로 응답할 수 있도록 가공하는 곳이다
    // 오류 정보를 JSON 또는 XML 형식으로 변환하는 데 활용할 수 있다

    private final MessageSource messageSource;
    // getMessage 메서드 사용을 위해
    // messageSource 빈 주입
    private final HttpServletRequest request;
    // 요청 쪽에 있는 브라우저 언어를 위해 사용. Locale 설정을 위해서!

    public Map<String, List<String>> getErrorMessage(Errors errors) {
        // 필드별로 있는 오류 메세지이므로 Map 형태로 가공하였다
        // 필드마다 나오는 에러는 여러개일 수 있다
        // 그렇게 때문에 리스트 형태로 맵을 구성했다

        // FieldErrors 처리
        Map<String, List<String>> messages = errors.getFieldErrors()
                // 에러 코드의 정보가 담긴 에러를 가지고 온다
                // 필드의 에러메세지를 담고 있는게 getFieldErrors이다
                .stream() // 스트림으로 만들고
                .collect(Collectors.toMap(FieldError::getField, e -> getCodeMessages(e.getCodes()))); // 필드명이 키가 되고, 발생한 에러가 리스트 형태로 값에 들어간다
                // Collectors.toMap(): 스트림 데이터를 맵(Map)으로 변환
                // FieldError 객체들의 스트림을 필드 이름을 키값으로, 해당 필드의 오류 메시지 목록을 값으로 하는 Map 형태로 변환

        // 모아서 맵 형태로 만든다
        // 키값이 필드명, 값에는 에러 메세지를 리스트 형태로 만들어 주었다
        
        //GlobalErrors 처리
        List<String> gMessages = errors.getGlobalErrors() // 전체 검증 과정에서 발생한 글로벌 오류 목록을 반환
                .stream()
                .flatMap(e -> getCodeMessages(e.getCodes()).stream()).toList();
        // 오류 메시지 목록을 가져오고, 스트림 형태로 변환하고 다시 리스트 형태로 변환..?

        if (!gMessages.isEmpty()) { // 글로벌 오류가 존재하는 경우
            messages.put("global", gMessages);
        } // 글로벌 오류가 존재하는 경우, 해당 오류 메시지들을 "global" 키 아래 messages에 저장하는 역할

        return messages;
    }

    public List<String> getCodeMessages(String[] codes){
        // 코드가 들어 오게 되면

        ResourceBundleMessageSource ms = (ResourceBundleMessageSource) messageSource;
        // messageSource에서 메시지를 가지고 와야 한다
        
        ms.setUseCodeAsDefaultMessage(false);
        // 메세지가 없을 때 에러코드를 메세지로 대체하는걸 false로 설정함
        // 메세지를 추출할때만 false로 설정

        List<String> messages = Arrays.stream(codes)
                .map(c -> {
                    // 왜 try catch를 했냐면 실제로 코드가 있는 메세지만 가지고 오기 위해서이다
                    try { // 원래 비어 있으면 오류가 나기 때문에 예외 처리를 한다
                        return ms.getMessage(c, null, request.getLocale()); // 교체될 부분이 없기 때문에 null로 만들었다
                        // 사용자가 사용하는 브라우저에서 언어 정보를 가지고 오기 위해 request.getLocale()
                    } catch (Exception e) {
                        return ""; // 없으면 비우고 List로 만든다?!
                        // 비어 있는 문자열로 반환
                    }
                })
//                .map(c -> messageSource.getMessage(c, null, request.getLocale()))
                .filter(s -> !s.isBlank()) // 없는 메세지는 나오지 않고 우리가 등록한 코드에 있는 메세지만 나온다
                .toList();

        ms.setUseCodeAsDefaultMessage(true); // 싱글톤이기 때문에 다시 원래 상태인 true로 바꿔 주어야 한다
        // 쓸때만 바꾸고 다 쓰면 돌려 주어야 한다
        return messages;
    }
}
