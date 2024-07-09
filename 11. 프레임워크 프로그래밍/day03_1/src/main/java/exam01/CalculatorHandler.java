package exam01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CalculatorHandler implements InvocationHandler {
// InvocationHandler는 자바에서 동적 프록시를 구현할 때 사용되는 인터페이스
// 동적 프록시는 런타임 시에 인터페이스를 구현하는 객체를 만들어내는 기술이며, 이 때 InvocationHandler는 프록시 객체가 수행할 작업을 정의함
// InvocationHandler를 구현한 객체는 실제로 동적으로 생성된 프록시 객체(가짜..? 임시클래스)가 메소드를 호출할 때마다 invoke 메소드가 호출됨

    private Object obj;

    public CalculatorHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable { // proxy: 메소드 호출을 받은 프록시 객체, method: 호출된 메소드에 대한 메타데이터, args: 메소드에 전달된 인수
        // 호출이 되면 이 메서드 안으로 유입이 된다. 여기를 거쳐서 간다
        // 메서드 안에서는 전후에 코드를 추가할 수 있다.
        // 메서드를 사용하는 이유는 통제를 하며 코드를 추가하기 위함이다.

        long stime = System.nanoTime(); // 추가 기능 - 공통 기능, 공통 관심사
        try {
            System.out.println("여기를 거쳐서 갑니다");
            Object result = method.invoke(obj, args);
            // Calculator factorial  호출 / 핵심 기능을 대신 수행

            return result;
        } finally {
            long etime = System.nanoTime(); // 추가 기능 - 공통 기능, 공통 관심사
            System.out.printf("걸린 시간 : %d%n", etime - stime);
        }
    }
}
