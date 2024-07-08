package exam01;

import java.util.Arrays;
import java.util.Comparator;

public class Ex07 {
    public static void main(String[] args) {
        Integer[] nums = {22, 10, 5, 1, 99, 50};
        Arrays.sort(nums, Comparator.reverseOrder());
        System.out.println(Arrays.toString(nums));
        // Arrays.sort(nums);
        // 기본 정렬, Integer 기본 정렬 기준 - java.lang.Comparable, int compareTo(...)
        // comparator가 필요할때는 wrapper클래스 형태로 변경하고 사용
    }   // 기본 정렬 기준은 대부분 오름차순으로 되어있음
}
