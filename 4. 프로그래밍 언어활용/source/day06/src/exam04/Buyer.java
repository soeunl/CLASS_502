package exam04;

public interface Buyer {
    void buy();
    
    default void order() {
        System.out.println("Buyer에서 주문"); 
        // 인터페이스 내부에서 인스턴스메서드도 정의가 가능하지만 default로 명시해주어야 함
        privateMethod();
    }
    private void privateMethod() {}
}
