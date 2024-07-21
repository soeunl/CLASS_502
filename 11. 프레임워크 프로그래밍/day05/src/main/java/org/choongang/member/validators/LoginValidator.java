package org.choongang.member.validators;

import lombok.RequiredArgsConstructor;
import org.choongang.member.controllers.RequestJoin;
import org.choongang.member.controllers.RequestLogin;
import org.choongang.member.entities.Member;
import org.choongang.member.mappers.MemberMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class LoginValidator implements Validator {

    private final MemberMapper mapper;

    @Override
    public boolean supports(Class<?> clazz) { // 검증 하고자 하는 커맨드 객체를 한정
        return clazz.isAssignableFrom(RequestLogin.class); // 넘어 오는 자료형은 RequestLogin으로 한정
    }

    @Override
    public void validate(Object target, Errors errors) {

        // Bean Validation 검증 실패 시에는 다음 검증을 진행X
        if(errors.hasErrors()) {
            return; // 만약 오류가 있다면, 더 이상의 검증을 진행하지 않고 종료
        }

        /**
         * 2) email로 회원이 조회되는지 검증
         * 3) 조회된 회원의 비밀번호가 입력한 값과 일치하는지 검증
         */

        RequestLogin form = (RequestLogin) target; // target: 검증 대상 객체 (RequestLogin 객체)
        String email = form.getEmail();
        String password = form.getPassword(); // 사용자가 입력한 비밀번호

        if(StringUtils.hasText(email)) {

            Member member = mapper.get(email);

            if (member == null) {
                // errors.rejectValue("email", "Check.emailPassword");
                errors.reject("Check.emailPassword");
            }

            if (member != null && StringUtils.hasText(password) && !BCrypt.checkpw(password, member.getPassword())) {
                // errors.rejectValue("password", "Check.emailPassword");
                errors.reject("Check.emailPassword");
            }
        }
    }
}
