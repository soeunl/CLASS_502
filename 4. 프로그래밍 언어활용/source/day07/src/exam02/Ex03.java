package exam02;

public class Ex03 {
    public static void main(String[] args) {
        try {
            int num1 = 10;
            int num2 = 2;
            int result = num1 / num2; // ArithmeticException

            String str = null;
            System.out.println(str.toUpperCase()); // NullPointerException

            System.out.println(result);
        } catch (ArithmeticException | NullPointerException e) {
            e.printStackTrace();
        } // 여러개의 예외 타입을 붙여서 함께 표시할 수 있음
        System.out.println("매우 중요한 코드...");
    }
}
