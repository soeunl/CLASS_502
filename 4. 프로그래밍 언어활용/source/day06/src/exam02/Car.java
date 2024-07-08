package exam02;

public abstract class Car {
    public abstract void start();
    public abstract void press();
    public abstract void turnoff();

    public final void run() { // 템플릿 메서드 패턴
        // 정해진 순서의 템플릿을 미리 마련해 놓는 것
        // 정해진 순서를 따라야 하는 경우에는 final 예약어를 붙여줌
        start();
        press();
        turnoff();
    }
}
