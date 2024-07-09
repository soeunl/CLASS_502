package config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect // 공통 기능을 정의하겠음
public class ProxyCalculator {

    @Pointcut("execution(* exam01..*(..))") // 범위에 대한 부분. 지금은 exam01쪽에 모든 메서드를 의미한다
    public void publicTarget() {}

    @Before("publicTarget()") // "publicTarget"이라는 이름을 가진 모든 public 메소드의 실행 전에 Advice를 실행하겠다는 의미
    public void before(JoinPoint joinPoint) {
        System.out.println("Before()");
    }

    @After("publicTarget()")
    public void after(JoinPoint joinPoint) {
        System.out.println("After()");
    }

    @AfterReturning(value="publicTarget()", returning = "returnValue")
        public void afterReturning(JoinPoint joinPoint, Object returnValue) {
            System.out.println("AfterReturning:" + returnValue);
        }

        @AfterThrowing(value="publicTarget()", throwing = "e")
        public void afterThrowing(JoinPoint joinPoint, Throwable e) {
            System.out.println("AfterThrowing:");
            e.printStackTrace();
        }

    @Around("publicTarget()") // 메서드 호출 전후에 공통 기능을 정의할 수 있음 
    // 이 메서드명을 가지고 저 범위 안에서 적용이 된다..?
    // 이 패턴을 가지고 공통으로 실행된다
    
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {

        Signature sig = joinPoint.getSignature(); // 호출된 메서드 정의 정보
        System.out.println(sig.toLongString());

        Object[] args = joinPoint.getArgs(); // 인수 정보
        System.out.println(Arrays.toString(args));
        
        Object obj = joinPoint.getTarget(); // 호출한 객체가 누구인지 알 수 있음
        System.out.println(obj.getClass());

        System.out.println("여기 거쳐서 가게 됩니다!!");

        long stime = System.nanoTime(); // 공통 기능

        boolean re = false;
        if (!re) {
            throw new RuntimeException("예외 테스트");
        }

        try {
            Object result = joinPoint.proceed(); // 핵심 기능 대신 실행 - factorial
            
            return result;

        } finally {
            long etime = System.nanoTime();
            System.out.printf("걸린 시간: %d%n", etime - stime);
        }
    }
}
