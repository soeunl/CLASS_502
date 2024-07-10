package member.validators;

import global.validators.Validator;
import member.controllers.RequestJoin;
import member.controllers.RequestLogin;
import org.springframework.stereotype.Component;

@Component
public class LoginValidator implements Validator<RequestLogin> {

    @Override
    public void check(RequestLogin form) {

    }
}
