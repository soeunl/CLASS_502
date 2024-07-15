package org.choongang.member.services;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.choongang.member.entities.Member;
import org.choongang.member.mappers.MemberMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final HttpSession session;
    private final MemberMapper mapper;

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
}
