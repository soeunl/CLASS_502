package exam01;

public class Ex02 {
    public static void main(String[] args) {
        double num = 123.12345678;
        System.out.printf("%10.2f%n", num);
        // %10 부분은 소수점 앞자리를 10자리로 한다는 뜻이다. %010으로 하면 소수점 앞자리의 빈자리는 0으로 채워짐. 소숫점도 자리 하나로 한다.
        // .2f 부분은 소수점 뒷자리를 두번째 자리까지만 표현한다는 뜻이다.
        System.out.printf("%.2f%n", num);
        // %.부분은 소수점 앞자리는 그대로 표현한다는 뜻이다.
        // .2f 부분은 소수점 뒷자리를 두번째 자리까지만 표현한다는 뜻이다.
    }
}
