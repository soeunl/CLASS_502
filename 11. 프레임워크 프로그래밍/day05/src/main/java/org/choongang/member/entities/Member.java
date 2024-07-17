package org.choongang.member.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
// @Table("CH_MEMBER")
public class Member {
    @Id // 기본키로 되어 있음을 반드시 명시
    private Long seq; // 지네릭으로 어짜피 바뀌므로 기본형 대신 래퍼클래스로 바꾸었다
    private String email;
    @JsonIgnore // JSON 변환에서 배제가 된다
    private String password;
    private String userName;
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDt;
}
