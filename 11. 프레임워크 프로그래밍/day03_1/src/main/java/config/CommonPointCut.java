package config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CommonPointCut {

    @Pointcut("execution(* exam01..*(..))")
    public void publicTarget() {}
}
