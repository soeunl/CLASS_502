package exam03;

public class Ex05 {
    public static void main(String[] args) {
        int num1 = 100;
        Integer num2 = Integer.valueOf(200);

        int result1 = num1 + num2; // num2.intValue() 자동으로 붙는다.
        System.out.println(result1);
        // 객체는 연산이 되지 않기 때문에 컴파일러가 자동으로 정수형태의 기본형으로 바꾸어 준다.

        Integer num3 = Integer.valueOf(100);
        Integer num4 = Integer.valueOf(200);

        int result2 = num3 + num4;
        System.out.println(result2);
    }
}
