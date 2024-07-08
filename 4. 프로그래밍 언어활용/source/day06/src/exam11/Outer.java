package exam11;

public class Outer {

    int num1 = 10;
    static int num2 = 20;

    static class Inner {
        public void method() {
            System.out.println("정적 내부 클래스에서 호출");
        // System.out.println(num1); Outer 클래스의 객체가 되든 관련이 없음
        // 객체 생성과 관련이 없으므로 Outer 클래스의 변수에 접근 불가
            System.out.println(num2); // 객체 생성과 관련이 없으므로 접근이 가능함
        }
    }
}
