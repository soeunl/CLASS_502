package exam01;

public class Ex05 {
    public static void main(String[] args) {
        int num1 = 10;

        num1 = 20;

        final int num2 = 30; // 상수
        // num2 = 40; // 값을 재할당할 수 없다

        final int num3;
        num3 = 30;
        // 최초 한번만 상수는 대입 가능

        System.out.println(num3);
    }
}
