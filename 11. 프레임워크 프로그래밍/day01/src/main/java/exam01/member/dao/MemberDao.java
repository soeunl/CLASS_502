package exam01.member.dao;


import exam01.member.entities.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component // 구성요소의 특징이 들어가기 때문에 Component. 특성을 알려주면 컨테이너 안에 들어간다
public class MemberDao {
    // DB에 영구 저장
    // 지금은 DB가 없어서 메모리에 저장하겠음

    private static Map<String, Member> members = new HashMap<>();

    public void register(Member member) {
        members.put(member.getEmail(), member);
    }

    public Member get(String email) {
        return members.get(email);
    }

    // 회원 목록 조회
    public List<Member> getList() {
        List<Member> data = new ArrayList<>(members.values());

        return data;
    }
}
