package exam01.member.services;

import exam01.global.validators.Validator;
import exam01.member.controllers.RequestJoin;
import exam01.member.controllers.RequestLogin;
import exam01.member.dao.MemberDao;
import exam01.member.entities.Member;
import exam01.member.validators.JoinValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    // 의존성
    @Autowired // 의존성을 찾아서 주입해주면서 null오류가 발생하는 것을 방지한다
    private JoinValidator validator;
    @Autowired
    // @Qualifier("mDao") // 빈의 이름이 이거라고 알려주면 해당 객체 찾아서 의존성을 주입해준다
    private MemberDao memberDao;

    // 의존하는 관계이다
    // 필수이다. 없으면 객체가 생성되지 않는다!
//    public JoinService(JoinValidator validator, MemberDao memberDao) {
//        this.validator = validator;
//        this.memberDao = memberDao;
//    }
    
    // 연관 관계 - 없어도 객체는 생성 된다.
    // setter를 통한 주입
    // public void setValidator(JoinValidator validator) {
    // this.validator = validator;
    // }

    public void process (RequestJoin form) {
        validator.check(form); // joinService는 validator 객체, form 객체를 의존 -> 의존성
        // 데이터 영구 저장 - DAO(Data Access Object)

        Member member = Member.builder()
                        .email(form.getEmail())
                                .password(form.getPassword())
                                        .userName(form.getUserName())
                                                .regDt(form.getRegDt())
                                                        .build();
        memberDao.register(member);
    }
}
