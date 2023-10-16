package com.board.spring_board.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimeAop {

    @Pointcut("@annotation(com.board.spring_board.aop.annotation.TimeAop)")
    private void pointCut() {}

    @Around("pointCut()") // 시간 측정 용도
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object target = proceedingJoinPoint.proceed();

        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds() + "초");
        return target;
    }
}
