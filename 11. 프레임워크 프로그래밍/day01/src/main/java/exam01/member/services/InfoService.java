package exam01.member.services;

import exam01.member.dao.MemberDao;
import exam01.member.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service // 큰 기능은 서비스, 구성요소는 컴포넌트이기 때문에 service라고 적는다. component 라고 해도 되긴하다
public class InfoService {

    private MemberDao memberDao;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired // 찾아서 의존성 주입해준다
    @Qualifier("memberDao") // 빈의 이름이 이거라고 알려주면 해당 객체 찾아서 의존성을 주입해준다
    public void setMemberDao(Optional<MemberDao> opt) {
        this.memberDao = opt.get();
    }

    //    @Autowired // 찾아서 의존성 주입해준다
//    public void setMemberDao(MemberDao memberDao) {
//        this.memberDao = memberDao;
//    }

//     @Autowired(required = false) // DateTimeFormatter 빈이 없으면 호출조차 되지 않는다
////     기본값은 true. 기본값이면 무조건 객체가 @Bean을 달고 객체컨테이너 안에 있어야 한다.
////     빈이 없고 false일때는 메서드가 호출조차 되지 않는다. 의존성 주입도 안된다
////     무시해버린다. 아예 실행이 안된다
//     public void setFormatter(DateTimeFormatter formatter) {
//        this.formatter = formatter;
//     }

    @Autowired
    public void setFormatter(Optional<DateTimeFormatter> opt) {
        System.out.println("호출호출!!!"); // Optional은 Bean이 없어도 실행 된다.
        this.formatter = opt.orElse(null);
    }

//    @Autowired
//    public void setFormatter(DateTimeFormatter formatter) { // @Nullable을 하면 null값이 주입되어서 메서드가 실행된다
//        System.out.println("호출!");
//        this.formatter = formatter;
//    }


    public void printList() {
        List<Member> members = memberDao.getList();
        members.forEach(m -> {
            System.out.println(m);
            LocalDateTime regDt = m.getRegDt();
            if(formatter != null) {
                System.out.println(formatter.format(regDt));
            }
        });
    }
}
