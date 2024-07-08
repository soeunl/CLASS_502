package exam01;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Ex04 {
    public static void main(String[] args) {
        // HashSet<String> names = new HashSet<>();
        // Set<String> names = new TreeSet<>();

        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
               // return o1.compareTo(o2); // 오름차순
               // return o2.compareTo(o1); // 내림차순
                return o1.compareTo(o2) * -1; // 내림차순
            }
        };

        Set<String> names = new TreeSet<>(Comparator.reverseOrder());

        names.add("이름1");
        names.add("이름2");
        names.add("이름2");
        names.add("이름3");
        names.add("이름4");
        names.add("이름5");

        System.out.println(names); // names.toString()
        // 순서는 중요하지 않음
        // 순서를 바꾸어도 처음 순서로 동일하게 출력됨
        // 검색이 주 목적이기 때문임
    }
}
