package exam01;

import java.util.function.BiFunction;

public class Ex02 {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> calc = (a, b) -> a + b;
        int result = calc.apply(10, 20);
        System.out.println(result);

        /**
         * BiFunction 참조자료형만 사용가능
         * 매개변수 2개, 반환값 1개여서 Integer가 3개임
         * Integer가 연산을 위해서는 오토박싱과 언박싱을 반복해야 하기 때문에 효율이 떨어짐
         * 1. 연산을 위해서 Integer -> int(언박싱), 반환값 Integer로 변환 (오토박싱), int result로 변환 -> (언박싱) -> 성능저하, 연산은 그냥 기본형으로 하는게 좋다!
         * 2. 자료형 반복 -> BinaryOperator<T>로 해결
         */
    }
}
