package com.board.spring_board.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ReturnAop {

    @Pointcut("@annotation(com.board.spring_board.aop.annotation.ReturnAop)")
    private void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object target = proceedingJoinPoint.proceed();
        System.out.println("리턴: " + target);
        return  target;
    }
}
