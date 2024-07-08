package exam01;

import java.util.Arrays;

public class Ex02 {
    public static void main(String[] args) {
        int[] num1 = {10, 20, 30, 40, 50, 60};
        int[] num2 = Arrays.copyOf(num1, 3);
        int[] num3 = Arrays.copyOfRange(num1, 1, 5); // 1, 2, 3, 4
        System.out.println(Arrays.toString(num1));
        System.out.println(Arrays.toString(num2));
        System.out.println(Arrays.toString(num3));
    }
}
