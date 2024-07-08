package exam02;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Ex08 {
    public static void main(String[] args) {
        int[] nums = {22, 11, 10, 2, 6, 8, 33};
        Arrays.stream(nums).map(x -> x * x).filter(x -> x % 2 == 1)
        // 어떤 작업을 할지 명시만 한 중간연산
                .forEach(System.out::println);
        // .forEach는 최종연산
    }
}
