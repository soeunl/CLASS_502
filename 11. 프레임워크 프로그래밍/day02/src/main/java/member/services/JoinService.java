package member.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import member.controllers.RequestJoin;
import member.dao.MemberDao;
import member.entities.Member;
import member.validators.JoinValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final JoinValidator validator; // final이 붙어 있으면 반드시 초기화가 필요하기 때문에 생성자 매개변수에 꼭 들어가야한다
    // 하지만 @RequiredArgsConstructor를 사용하면 자동으로 매개변수로 넣어진 생성자를 만들어준다!

    @NonNull // 생성자 매개변수에 넣어준다. NonNull은 final과 달리 값을 바꿀 수 있다
    private MemberDao memberDao;


    public void process(RequestJoin form) {
        validator.check(form);  // joinService는 validator 객체, form 객체를 의존 -> 의존성

        // 데이터 영구 저장 - DAO(Data Access Object)
        Member member = Member.builder()
                        .email(form.getEmail())
                        .password(form.getPassword())
                        .userName(form.getUserName())
                        .regDt(LocalDateTime.now())
                        .build();

        memberDao.register(member);
    }
}
