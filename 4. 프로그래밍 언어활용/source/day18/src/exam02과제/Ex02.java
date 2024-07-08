package exam02과제;

import java.time.LocalDate;

public class Ex02 {
    public static void main(String[] args) {

        int n = 30;

        System.out.println("   일  월  화  수  목  금  토");
        System.out.print("    ");
        for (int i = 1; i <= n; i++) {
            if (i < 10) {
                System.out.print("  " + i + " ");
            }
            if (i >= 10) {
            System.out.print(" " + i + " ");
            }
            if (i % 7 == 0) {
                System.out.println();
            }
        }
    }
}