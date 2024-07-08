package exam01;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex01 {
    public static void main(String[] args) {
        // 변경 불가 List (추가하거나 삭제하는 것은 되지 않음)
        List<String> names = Arrays.asList("이름1", "이름2", "이름3");
        System.out.println(names);
        
        // 변경이 필요한 경우는 List의 구현 클래스의 객체 생성시 생성자 매개변수로 대입
        List<String> names2 = new ArrayList<>(names);
        names2.remove(0);
        names2.add("이름4");
        names2.add("이름5");
        names2.remove(3);
        System.out.println(names2);
    }
}
