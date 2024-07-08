package exam01;
// T는 Fruit의 하위클래스
public class Box<T extends Fruit & Eatable> { // Box<T> : 지네릭 클래스, T - 타입 매개변수, Box : 원시타입
    // Box : 원시타입
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public String toString() {
        // T == Object
        // item.get() => 여기서는 Object이기 때문에 get 메서드가 보이지 않음
        // Fruit로 상속받은 상태에서는 T == Fruit
        return item.get();
    }
}

// T는 Fruit의 하위 클래스, 충분한 정보가 있으므로 Object를 Fruit로
