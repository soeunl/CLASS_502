package exam03;

public class Ex01 {
    public static void main(String[] args) {
         Resources res = new Resources();
            Resources2 res2 = new Resources2();
            try (res; res2){
            // res가 AutoClosable 인터페이스 구현 객체인지 체크
            // AutoClosable 인터페이스 구현 객체일때만 가능 -> close() 메서드 자동 호출 // 이것이 기준
            // AutoCloseable 인터페이스에는 close() 메서드가 있고 이를 구현한 클래스는 close()를 명시적으로 호출하지 않아도 close() 메서드 부분이 호출되기 때문이다.

            AutoCloseable auto = res;
            auto.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
