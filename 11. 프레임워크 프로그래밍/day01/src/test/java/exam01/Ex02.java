package exam01;

import exam01.member.Assembler;
import exam01.member.controllers.RequestJoin;
import exam01.member.controllers.RequestLogin;
import exam01.member.dao.MemberDao;
import exam01.member.entities.Member;
import exam01.member.services.JoinService;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Ex02 {

    @Test
    void test1() {
        Assembler assembler = new Assembler(); // 객체조립기
        JoinService joinService = assembler.joinService(); // 객체조립기를 통해 편하게 객체 생성
        MemberDao memberDao = assembler.memberDao();

        RequestJoin form = RequestJoin.builder()
                .email("user01@test.org")
                .userName("사용자01")
                .password("12345678")
                .confirmPassword("12345678")
                .build();

        joinService.process(form);

        List<Member> members = memberDao.getList();
        members.forEach(System.out::println);
    }
}
