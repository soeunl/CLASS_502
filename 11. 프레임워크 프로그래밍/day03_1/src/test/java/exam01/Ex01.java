package exam01;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

public class Ex01 {
    @Test
    void test1() {
        Object obj = Proxy.newProxyInstance(
          Calculator.class.getClassLoader(),
          new Class[] {Calculator.class},
          new CalculatorHandler(new RecCalculator()) // 매개변수로 주입한 것이 원래 호출하고자 하던 객체이다!
        );

        Calculator cal = (Calculator)obj;
        long result = cal.factorial(10L);
        System.out.println(result);
    }
}
