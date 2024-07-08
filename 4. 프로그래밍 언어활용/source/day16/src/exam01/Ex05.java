package exam01;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Ex05 {
    public static void main(String[] args) {
        String[] names = {"이름1", "이름1", "이름2", "이름3", "이름4", "이름5"};
//      List<String> namesList = Arrays.stream(names).collect(Collectors.toList());
        List<String> namesList = Arrays.stream(names).toList();
        System.out.println(namesList); // 중복 가능, 순서대로, 많이쓰임
        Set<String> namesSet = Arrays.stream(names).collect(Collectors.toSet()); // 집합형태, 중복이 없음, 순서 중요X 라서 아무렇게나 나옴
        System.out.println(namesSet);
    }
}
