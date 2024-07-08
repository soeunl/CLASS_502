package exam06;

public class Ex01 {
    public static void main(String[] args) {
        Book book = new Book(1000, "책1", "저자1");
        System.out.println(book); // book.toString()
        Class cls = Book.class;
        Class cls2 = book.getClass();
        // 모든 클래스의 정적변수로 추가됨
        // 클래스의 구성 정보가 담겨있는 객체 -> 정보를 확인해보기 위한 용도
    }
}
