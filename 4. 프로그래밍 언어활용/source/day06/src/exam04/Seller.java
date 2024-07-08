package exam04;

public interface Seller {
    void sell();

    default void order() {
        System.out.println("Seller에서 주문");
        // 인터페이스 내부에서 인스턴스메서드도 정의가 가능하지만 default로 명시해주어야 함
    }
    public static void staticMethod() {
        System.out.println();
    }
}
