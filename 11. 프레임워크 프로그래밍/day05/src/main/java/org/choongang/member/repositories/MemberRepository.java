package org.choongang.member.repositories;

import org.choongang.member.entities.Member;
import org.springframework.data.domain.Page; // 페이징 처리 관련 클래스(데이터 건수가 많은 경우 한 페이지씩 데이터를 가져오는 기능)
import org.springframework.data.domain.Pageable; // 페이징 처리를 위한 정보를 담는 객체
import org.springframework.data.jdbc.repository.query.Query; // 직접 SQL 쿼리를 작성하는 기능을 제공하는 애노테이션
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, Long> {
    // 지네릭에서 기본형은 되지 않는다

    Member findByEmail(String email); // 이메일 주소를 이용하여 회원 정보 조회
    Page<Member> findByUserNameContaining(String keyword, Pageable pageable); // 용자 이름에 특정 문자열이 포함된 회원 정보를 페이징 처리하여 조회
    List<Member> findByUserNameContainingAndEmailContainingOrderByRegDtDesc(String key1, String key2); // 사용자 이름과 이메일 주소에 특정 문자열이 모두 포함된 회원 정보를 등록 날짜순으로 내림차순 정렬하여 조회

    @Query("SELECT * FROM MEMBER WHERE USER_NAME LIKE :param1 AND EMAIL LIKE :param2 ORDER BY REG_DT DESC") // 직접 SQL 쿼리를 사용하여 사용자 이름과 이메일 주소에 특정 문자열이 모두 포함된 회원 정보를 등록 날짜순으로 내림차순 정렬하여 조회
    List<Member> getMembers(@Param("param1") String key1, @Param("param2") String key2);
     // 사용자 이름과 이메일 주소에 특정 문자열이 모두 포함된 회원 정보를 등록 날짜순으로 내림차순 정렬하여 조회하고, 조회된 회원 정보 목록을 List<Member> 형태로 반환
}
