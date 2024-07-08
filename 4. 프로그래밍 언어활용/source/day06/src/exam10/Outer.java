package exam10;

public class Outer {

    int num1 = 10;

    class Inner{ // 인스턴스 내부 클래스
        int num1 = 15;
        int num2 = 20;
        public void method() {
            // Outer.this  외부 클래스의 자원을 접근할 때 사용
            System.out.println("인스턴스 내부 클래스에서 호출");
            System.out.printf("num1 + num2 = %d%n", Outer.this.num1 + num2);
        } // Outer.this를 통해 외부 클래스 자원에 접근
    }
}
