package exam01;

import config.AppCtx;
import mappers.member.MemberMapper;
import member.entities.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@Transactional // 테스트 진행 후 롤백이 된다
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppCtx.class)
public class Ex03 {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    void test1() {
        long total = memberMapper.getTotal();
        System.out.println(total);
    }

    @Test
    void test2() {
        Member member = Member.builder()
                .email("user08@test.org")
                .password("12345678")
                .userName("유저08")
                .build();

        int result = memberMapper.register(member);

        Member member2 = memberMapper.get(member.getEmail());
        System.out.println(result);

        int exists = memberMapper.exists(member.getEmail());
        System.out.println(exists);
    }
}
