package member;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Member {
    // DB에 있는 데이터를 담아줄 목적의 클래스
    // DB의 필드명과 동일해야 자동 mapping이 된다.
        private long userNo;
        private String userId;
        private String userPw;
        private String userNm;
        private String mobile;
        private LocalDateTime regDt;
        private LocalDateTime modDt;
    }
