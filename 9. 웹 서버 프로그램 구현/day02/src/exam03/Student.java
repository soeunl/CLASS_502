package exam03;

public class Student {
    // 객체를 만들기 위한 설계도
    // 변수와 함수를 만들기 위한 설계를(정의를) 넣음
    static int id;
    String name;
    String subject;

    public Student() {};

    void study() {
        System.out.printf("id=%d, name=%s, subject=%s%n", id, name, subject
        );
    }
}
