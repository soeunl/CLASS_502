package exam01;

public class Outer {

    private Calculator cal = new Calculator() { // 멤버변수에 대입
        @Override
        public int add(int num1, int num2) {
            return num1 + num2;
        }
    };

    public Calculator method(int num3) {
        Calculator cal = new Calculator() { // 지역내부
            public int add (int num1, int num2) {
                // num3 = 100; 지역변수의 상수화 final int num3 형태로 변환
                return num1 + num2 + num3;
            } // 실행될 때 Calculator객체가 만들어진다.
        };
        return cal;
    }
}
//1. 지역내부와 멤버변수
//2. 미구현된 메서드 구현
//= 객체가 된다.
