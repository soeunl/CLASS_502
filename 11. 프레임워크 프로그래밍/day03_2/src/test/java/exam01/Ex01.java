package exam01;

import config.AppCtx;
import member.entities.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppCtx.class)
public class Ex01 {

    @Autowired // test에서는 final을 쓰지 못하므로 Autowired를 사용해야 한다
    private JdbcTemplate jdbcTemplate;

    @Test
    void test1() {
        String sql = "INSERT INTO MEMBER (SEQ, EMAIL, PASSWORD, USER_NAME)" +
                "VALUES (SEQ_MEMBER.NEXTVAL, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, "user03@test.org", "123456", "사용자03");

        System.out.println(result);
    }

    @Test
    void test2() { // 여러 데이터 조회
        List<Member> members = jdbcTemplate.query("SELECT * FROM MEMBER",
                this::mapper);
                        members.forEach(System.out::println);
    }

    /*    Member.builder()
                .seq(rs.getLong("SEQ"))
                .email(rs.getString("EMAIL"))
                .password(rs.getString("PASSWORD"))
                .userName(rs.getString("USER_NAME"))
                .regDt(rs.getTimestamp("REG_DT").toLocalDateTime())
                .build()); */



    @Test
    void test3() { // 단일 데이터 조회
        String email = "user05@test.org";
        try {
            Member member = jdbcTemplate.queryForObject("SELECT * FROM MEMBER WHERE EMAIL = ?", this::mapper, email);
            System.out.println(member);
        } catch (Exception e) {
            System.out.println("없음");
        }
    }

    @Test
    void test4() {
        int total = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM MEMBER", int.class);
        System.out.println(total);
    }

    private Member mapper(ResultSet rs, int num) throws SQLException {
        return Member.builder()
                .seq(rs.getLong("SEQ"))
                .email(rs.getString("EMAIL"))
                .password(rs.getString("PASSWORD"))
                .userName(rs.getString("USER_NAME"))
                .regDt(rs.getTimestamp("REG_DT").toLocalDateTime())
                .build();
    }
}
