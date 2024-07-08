package exam02;

public class Ex04 {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer(100);
        sb.append("ABC");
        System.out.printf("sb 주소 ; %d%n", System.identityHashCode(sb));

        sb.append("DEF");
        System.out.printf("sb 주소 ; %d%n", System.identityHashCode(sb));

        sb.append("GHI");
        System.out.printf("sb 주소 ; %d%n", System.identityHashCode(sb));

        String str = sb.toString(); // 문자열을 Buffer에서 꺼내는 역할
        // 문자열을 Buffer에서 꺼낼 때 새로운 주소가 할당됨
        System.out.printf("str 주소 ; %d%n", System.identityHashCode(str));
        System.out.println(str);
    }
}
