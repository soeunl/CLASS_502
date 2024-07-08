package exam03;

public class Box<T> {
    private T item;

    // Box가 객체가 될 때 T가 결정 - 지네릭 클래스의 T
    public void method1(T str1, T str2) {
        
    }

    // 지네릭 메서드 - 호출 시 T의 자료형이 결정 - 지네릭 메서드의 T
    public <T> void method2(T str1, T str2) {

    }
}

// 두개의 T는 같은 T가 아니다
