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
public class ArgsAop {

    @Pointcut("@annotation(com.board.spring_board.aop.annotation.ArgsAop)")
    private void pointCut() {}

    @Around("pointCut()") // 추출 용도
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Signature signature = proceedingJoinPoint.getSignature();
        CodeSignature codeSignature  = (CodeSignature) signature;

        String className = codeSignature.getDeclaringTypeName(); // 클래스명
        String methodName = codeSignature.getName(); // 메서드명
        String[] argNames = codeSignature.getParameterNames();
        Object[] args = proceedingJoinPoint.getArgs();
        System.out.println("클래스명: " + className);
        System.out.println("메서드명: " + methodName);
        for(int i = 0; i < argNames.length; i++) {
            System.out.println(argNames[i] + ": " + args[i]);
        }
        // 테스팅 용도로 유용함

        Object target = proceedingJoinPoint.proceed();
        return target;
    }

}
