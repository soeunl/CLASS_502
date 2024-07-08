package exam01;

public class Ex06 {
    public static void main(String[] args) {
        int total = 0;
        for (int i = 0, j = 10; i <=100; i++, j += 2) {
            total += i;
            System.out.println("j" + j);
        }
        System.out.println("합계" + total);
    }
}
