package exam01;

public class Ex01 {
    public static void main(String[] args) {
        Outer outer = new Outer(); // Outer 클래스를 객체로 생성
        Calculator cal2 = outer.method(30);
        int result = cal2.add(10,20);
        System.out.println(result);
    }
}
