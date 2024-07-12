package org.choongang.member.mappers;


import org.apache.ibatis.annotations.Select;
import org.choongang.member.entities.Member;

public interface MemberMapper {
    @Select("SELECT COUNT(*) FROM MEMBER") // 간단한 쿼리는 여기서도 작성 가능, 하지만 복잡한 쿼리는 Mapper에 작성
    long getTotal();

    int register(Member member);
    Member get(String email);
    int exists(String email);
}
