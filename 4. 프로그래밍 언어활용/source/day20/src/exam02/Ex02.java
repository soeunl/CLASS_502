package exam02;

public class Ex02 {
    public static void main(String[] args) {
        DecorateCalculator cal1 = new DecorateCalculator(new ImplCalculator());
        long result1 = cal1.factorial(10L);
        System.out.println(result1);

        DecorateCalculator cal2 = new DecorateCalculator(new ImplCalculator());
        long result2 = cal1.factorial(10L);
        System.out.println(result1);
    }
}
