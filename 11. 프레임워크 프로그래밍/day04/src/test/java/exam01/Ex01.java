package exam01;

import config.AppCtx;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Transactional // 자동으로 트랜잭션을 시작하고, 메서드 실행이 완료될 때 커밋(commit) 또는 롤백(rollback)을 수행
@ExtendWith(SpringExtension.class) // 테스트 클래스가 스프링 테스트 컨텍스트와 통합되어 스프링 관련 기능을 사용할 수 있게 됨
@ContextConfiguration(classes = AppCtx.class) // 테스트가 실행될 때 필요한 스프링 빈들을 설정 파일(AppCtx.class)을 통해 로드할 수 있게 됨
public class Ex01 {
    // 테스트에서는 생성자를 쓸 수 없기 때문에 @Autowired를 써야 한다

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void test() {
//        int result = jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                String sql = "INSERT INTO MEMBER (SEQ, EMAIL, PASSWORD, USER_NAME)" + "VALUES                     (SEQ_MEMBER.NEXTVAL, ?, ?, ?)";
//
//                PreparedStatement pstmt = con.prepareStatement(sql);
//                pstmt.setString(1, "user04@test.org");
//                pstmt.setString(2, "12345678");
//                pstmt.setString(3, "사용자04");
//
//                return pstmt;
//            }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = jdbcTemplate.update(con -> {
            String sql = "INSERT INTO MEMBER (SEQ, EMAIL, PASSWORD, USER_NAME)" + "VALUES (SEQ_MEMBER.NEXTVAL, ?, ?, ?)";

               PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"SEQ"});
               pstmt.setString(1, "user05@test.org");
               pstmt.setString(2, "12345678");
               pstmt.setString(3, "사용자05");

              return pstmt;
        }, keyHolder);

        System.out.println(result);
        Number key = keyHolder.getKey();
        long seq = key.longValue();
        System.out.println(seq);
    }
}
