package exam01;

import java.util.SplittableRandom;

public class Student {
    static int id; // 학번
    String name; // 학생명
    String subject; // 과목

    // 기본 생성자
    public Student() {
        // 객체 생성 이후 실행되는 부분 - id, name, subject는 이미 변수가 된 상태
        // 멤버 변수의 초기화 작업을 주로 많이 정의
        id = 1000;
        name = "이이름";
        subject = "과목1";
    }

    public Student(int _id, String _name, String _subject) {
        id = _id; // 지역변수의 값을 가지고 생성자 매개변수를 초기화 한다
        name = _name;
        subject = _subject;
    }

    void study() {
        System.out.printf("id=%d, name=%s, subject=%s%n", id, name, subject);
    }
}
