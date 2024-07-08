package exam01.member.validators;

import exam01.global.validators.Validator;
import exam01.member.controllers.RequestJoin;
import exam01.member.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // 알려주면 싹 다 주입을 해준다
public class JoinValidator implements Validator<RequestJoin> {

    private MemberDao memberDao;

    @Autowired // setter메서드 위에도 정의 가능, 스프링 컨테이너 안에서 찾아보고 주입해준다
    // @Qualifier("memberDao") // 빈의 이름이 이거라고 알려주면 해당 객체 찾아서 의존성을 주입해준다
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public void check(RequestJoin form) {

    }
}