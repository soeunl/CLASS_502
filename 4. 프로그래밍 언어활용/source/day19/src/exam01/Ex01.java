package exam01;

public class Ex01 {
    public static void main(String[] args) {
        // %d(정수), %f(실수), %s(문자열), %c(문자)
        /**
         * %자리수s  -> %5s     공백3ab
         * %-자리수s  -> %-5s   ab공백3
         */

        System.out.print("----");
        System.out.printf("%10s", "ab"); // "%10s" 전체 자릿수를 10칸으로 하는데 왼쪽기준으로 공백을 채운다.
        System.out.println("----");

        System.out.print("----");
        System.out.printf("%-10s", "ab"); // "%-10s" 전체 자릿수를 10칸으로 하는데 오른쪽에 공백을 채운다.
        System.out.println("----");

        System.out.print("----");
        System.out.printf("%-10s", "ab");
        System.out.printf("ab", "%-10s"); // 첫번째 매개변수가 형식에 대한 것이라 자리를 바꾸면 안된다. 이렇게 쓰면 안됨
        System.out.println("----");

        System.out.print("----");
        System.out.printf("%05d", 100); // 5자리인데 남은 공간은 0으로 채워 넣는다. 0을 안하면 그냥 빈 공백만 나온다.
        System.out.println("-----");
    }
}
