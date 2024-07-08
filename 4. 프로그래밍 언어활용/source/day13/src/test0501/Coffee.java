package test0501;

public class Coffee {
    String name;
    int money;
    String cafe;
    String menu;

    public Coffee(String name, int money, String cafe, String menu) {
        this.name = name;
        this.money = money;
        this.cafe = cafe;
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "아침 출근길에 " + name + "씨는 " + money + "원을 내고 " + cafe + "에서 " + menu + "를 사 마셨습니다." ;
    }
}