package exam02;

public class C extends B {
    int numC = 30;

    public C() {
        super(); // 모든 생성자의 첫번째 라인에 자동으로 추가됨
        // B 클래스에 정의된 B();
        System.out.println("C 생성자!");
    }
}
