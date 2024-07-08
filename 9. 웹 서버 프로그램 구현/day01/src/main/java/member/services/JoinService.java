package member.services;

import global.validators.Validator;
import member.controllers.RequestJoin;

// 상속은 확장에 유리하지 않다. 구성의 방식을 사용해야 한다.
public class JoinService {

    private Validator<RequestJoin> validator;

    public JoinService(Validator<RequestJoin> validator) {
        this.validator = validator;
    }

    public void process(RequestJoin form) {
        validator.check(form);
    }

//    public void process(RequestJoin form) {
//        String email = form.getEmail();
//        if (email == null || email.isBlank()) {
//            throw new ValidationException("이메일을 입력하세요");
//        }
//        String password = form.getPassword();
//        if(password == null || password.isBlank()) {
//            throw new ValidationException("비밀번호를 입력하세요");
//        }
//    }
}
