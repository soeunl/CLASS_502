package member.services;

import global.Mailer;
import global.exceptions.ValidationException;
import global.validators.Validator;
import jakarta.servlet.http.HttpServletRequest;
import member.validators.LoginValidator;

// public class LoginService {
//    public void process(HttpServletRequest request) {
//        아이디 : email, 비밀번호: password
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        System.out.printf("email=%s, password=%s%n", email, password);
//        String test = request.getParameter("123");
//        if (email == null || email.isBlank()) {
//            throw new ValidationException("이메일을 입력하세요");

    public class LoginService {
        private Validator<HttpServletRequest> validator;
        private Mailer mailer;

        public LoginService(Validator<HttpServletRequest> validator) {
            this.validator = validator;
        }

        public void setMailer(Mailer mailer) {
            this.mailer = mailer;
        }

        public void process(HttpServletRequest request) {

            validator.check(request);

            // 로그인 성송 가정
            // 성공시 메일 전송
            if (mailer != null) {
                String email = request.getParameter("email");
                mailer.send(email);
            }
        }
    }
