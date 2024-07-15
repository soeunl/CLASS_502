package org.choongang.member.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.choongang.member.controllers.RequestLogin;
import org.choongang.member.entities.Member;
import org.choongang.member.mappers.MemberMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final HttpSession session;
    private final MemberMapper mapper;
    private final HttpServletResponse response;

    public void process(String email) {
        /**
         * 1. email로 회원 조회
         * 2. 세션에 회원 정보를 유지
         */

        Member member = mapper.get(email);
        if(email == null) {
            return;
        }

        session.setAttribute("member", member);
    }

    public void process(RequestLogin form) {
        process(form.getEmail()); // 이메일 기억하기 처리를 위함임
        /**
         * 이메일 기억하기 처리
         */
        // 쿠키는 만료 시간을 가지고 통제
        Cookie cookie = new Cookie("savedEmail", form.getEmail());
        if (form.isSaveEmail()) { // 쿠키 등록
            cookie.setMaxAge(60 * 60 * 24 * 7); // 7일간 쿠키 유지
        } else {
            // 쿠키 제거
            // 만료 시간이 지나면 쿠키가 제거되므로 만료시간을 없애면 된다
            cookie.setMaxAge(0);
        }
        response.addCookie(cookie);
    }
}
