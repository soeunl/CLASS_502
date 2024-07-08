package exam03;

public interface Calculator {
    // public abstract int add(...);
    int Num = 10; // 정적인 형태로 되어 있음, static + 정적 상수 형태
    // public static final 자동 추가
    // 객체를 만들지 않고 접근 가능한 형태로 추가되어 있음
    // 정적인 상수 형태이므로 대문자 사용

    int add(int num1, int num2); // public abstract 자동으로 추가
} // 추상메서드는 무조건 public abstract만 추가해준다
