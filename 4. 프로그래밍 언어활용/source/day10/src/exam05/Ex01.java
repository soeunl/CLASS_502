package exam05;

public class Ex01 {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<Apple>();
        appleBox.setItem(new Apple());
        // 형 변환 될 필요가 없음
        // 타입 안정성 확보

        Apple apple = appleBox.getItem();
        System.out.println(apple.get());
    }
}
