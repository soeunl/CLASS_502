package config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(2)
public class ProxyCalculator2 {

//    @Pointcut("execution(* exam01..*(..))")
//    public void publicTarget() {}

//    @Around("publicTarget()") // 위에서 정의한 적용 범위 내에서 적용된다

    @Around("config.CommonPointCut.publicTarget()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {

        long stime = System.nanoTime();

        try {
            Object result = joinPoint.proceed();

            return result;

        } finally {

            long etime = System.nanoTime();
            System.out.printf("걸린 시간: %d%n", etime - stime);
        }
    }
}
