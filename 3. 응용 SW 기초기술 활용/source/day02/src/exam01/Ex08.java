package exam01;
// 변수 두 개를 선언해서 20과 3.0을 대입하고 두 변수의 사칙연산 결과를 정수로 출력해 보시오
public class Ex08 {
    public static void main(String[] args) {
        int num1 = 20;
        double num2 = 3.0;

        int result = (int)(num1 * num2); // int num1 -> double
        System.out.println(result);
    }
}
