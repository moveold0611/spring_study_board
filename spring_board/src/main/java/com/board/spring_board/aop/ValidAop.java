package com.board.spring_board.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // aop 전용 어노테이션
@Component
public class ValidAop {

    @Pointcut("execution(* com.board.spring_board.controller.*.*(..))") // aop위치 설정
    private void pointCut() {}


    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("전처리");
        // 이 위로 전처리
        Object target = proceedingJoinPoint.proceed();
        // 이 아래로 후처리
        System.out.println("후처리");

        return target;
    }
    
    
}
