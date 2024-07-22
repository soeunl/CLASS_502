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

    private final HttpSession session; // HTTP 세션 객체 (필수 의존성)
    private final MemberMapper mapper; // 회원 정보를 조회하는 매퍼 (필수 의존성)
    private final HttpServletResponse response; // HTTP 응답 객체 (필수 의존성)

    public void process(String email) {
        /**
         * 1. email로 회원 조회
         * 2. 세션에 회원 정보를 유지
         */

        Member member = mapper.get(email); // mapper.get(email) 메서드를 사용하여 해당 이메일 주소에 해당하는 회원 정보를 조회
        if(email == null) {
            return; // 조회된 회원 정보가 null이면 아무 작업도 하지 않고 메서드를 종료
        }

        session.setAttribute("member", member); // 조회된 회원 정보가 존재한다면, 세션에 "member"라는 속성으로 회원 정보를 저장
    }

    public void process(RequestLogin form) {
        process(form.getEmail()); // 이메일 기억하기 처리를 위함임
        // 이메일 주소를 이용한 회원 정보 조회 및 세션 저장 로직을 수행
        /**
         * 이메일 기억하기 처리
         */
        // 쿠키는 만료 시간을 가지고 통제
        Cookie cookie = new Cookie("savedEmail", form.getEmail()); // Cookie 객체를 생성하여 "savedEmail" 키에 입력받은 이메일 주소를 저장
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
