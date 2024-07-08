package exam02;

public class B extends A { // B가 A를 상속
    int numB = 20;

    public B() {
        super(); // 모든 생성자의 첫번째 라인에 자동으로 추가됨
        // A 클래스에 정의된 A();
        System.out.println("B 생성자!");
    }
}
