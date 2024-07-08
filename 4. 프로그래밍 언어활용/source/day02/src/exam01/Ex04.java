package exam01;

public class Ex04 { // exam01.Ex04 -> 실제 클래스명
// 다른 클래스에 있으면 다른 함수임

    public static void main(String[] args) {
    int result1 = add(10, 20); // int add(int, int)
        System.out.println(result1);

    int result2 = add(10, 20, 30); // int add(int, int, int)
    System.out.println(result2);
    }

    static int add(int num1, int num2) {

        return num1 + num2;
    }

//    static  int add(int num3, int num4) {
//        return num3 + num4;
//    // 자료형만 체크하기 때문에 동일하게 인식

    static int add(int num1, int num2, int num3) {
        return num1 + num2 + num3;
    }

    static double add(double num1, double num2) {
        return num1 + num2;
    } // 함수의 이름만 가지고 구분하는 것이 아니라 자료형이나 반환값으로 구분
}
