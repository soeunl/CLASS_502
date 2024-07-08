package exam03;

public class CoffeeTest {
    public static void main(String[] args) {
        Coffee kim = new Coffee("김", 4000, "별다방", "아메리카노");
        Coffee lee = new Coffee("이", 4500, "콩다방", "라떼");

        System.out.println(kim);
        System.out.println(lee);
    }

}
