package exam03;

public class Ex04 {
    public static void main(String[] args) {
        String fruits = "Apple, Orange, Mango, Melon, Apple";
        System.out.printf("indexOf Apple : %d%n", fruits.indexOf("Apple"));
        // 왼쪽 -> 오른쪽 이동하며 찾음

        System.out.printf("lastIndexOf Apple : %d%n", fruits.lastIndexOf("Apple"));
        // 오른쪽 -> 왼쪽 이동하며 찾음

        System.out.printf("IndexOf Banana : %d%n", fruits.lastIndexOf("Banana"));
        // 없는 경우 -1
    }
}
