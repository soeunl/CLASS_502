package exam01;

public class Ex03 {
    public static void main(String[] args) {
        int num3 = 30; // 지역변수의 상수화 final int num3
        Calculator cal = new Calculator() {
            @Override
            public int add(int num1, int num2) {
                return num1 + num2 + num3;
            }
        };
        Calculator cal2 = (x, y) -> x + y;
    }
}
