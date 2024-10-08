package exam01;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ex12 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("이름3", "이름1", "이름2", "이름4", "이름5");
        Collections.sort(names, Comparator.reverseOrder()); // 기본 정렬 기준 (Natural Order - java.lang.Comparable, int compareTo(...))
        System.out.println(names);
    }
}
