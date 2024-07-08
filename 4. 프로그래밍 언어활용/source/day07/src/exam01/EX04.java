package exam01;

public class EX04 {
    public static void main(String[] args) {
        A a = new A() {
            @Override
            public void method() {
                System.out.println("재정의된 메서드");
            } // 객체를 만드는 과정 중에도 재정의가 가능함
        };
        a.method(); // 생성 과정중에도 정의가 가능하다
    }
}
