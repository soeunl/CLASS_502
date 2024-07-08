package exam01;

import java.sql.SQLOutput;

public class Student {
    int id; // 학번
    String name; // 이름
    String subject; // 과목

    public Student(int _id, String _name, String _subject) {
        id = _id;
        name = _name;
        subject = _subject;

        // 기본 생성자(Default 생성자)
        // 객체를 만들 수 있는 수단이 하나도 없을 때 생성
        // 객체를 만드는 역할
        // 내부적으로 구현되어 있고, 통제 못하게 막혀있음
        // 객체가 생성된 이후 실행 코드
        // 실행 시점? id, name, subject에 공간을 할당 받은 상태
        // System.out.println("객체 생성 이후 실행");
        // 인스턴스 변수의 초기화 작업을 주로 수행
        // 변수의 값을 할당하는 역할을 주로 생성자가 담당함
        // id = 1000;
        // name = "이이름";
        // subject= "자바";
    }

    void study() {
        System.out.printf("학번: %d, %s이 %s를 공부한다.%n", id, name, subject);
    // 이미 공간을 할당받은 상태에서 실행. 객체가 있어야지만 실행
        // 1) 객체 생성 2) 호출 - 호출 시점에는 인스턴스 변수가 이미 공간 할당
    }
}