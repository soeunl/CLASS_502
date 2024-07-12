package org.choongang.member.validators;

import org.choongang.global.validators.Validator;
import org.choongang.member.controllers.RequestLogin;
import org.springframework.stereotype.Component;

@Component
public class LoginValidator implements Validator<RequestLogin> {

    @Override
    public void check(RequestLogin form) {

    }
}
