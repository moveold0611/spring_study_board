package com.board.spring_board.aop;

import com.board.spring_board.exception.ValidCheckException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;

import java.util.HashMap;
import java.util.Map;

@Aspect // aop 전용 어노테이션
@Component
public class ValidAop {

//    @Pointcut("execution(* com.board.spring_board.controller.*.*(..))") // aop위치 설정

    @Pointcut("@annotation(com.board.spring_board.aop.annotation.ValidAop)")
    private void pointCut() {}

//    @Pointcut2("execution(* com.board.spring_board.controller.SignupController.*(..))")
//    @Pointcut3("execution(* com.board.spring_board.controller.BoardController.*(..))")
    // @Around("pointCut2() || pointCut3()")

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        BeanPropertyBindingResult bindingResult = null;

        for(Object arg :args) { // aop 타켓 메서드의 body에서 bindingResult를 꺼내서 다운캐스팅 / 꺼내기
            if(arg.getClass() == BeanPropertyBindingResult.class) {
                bindingResult = (BeanPropertyBindingResult) arg;
                break;
            }
        }

        if(bindingResult == null) { // null check
            return proceedingJoinPoint.proceed();
        }

        if(bindingResult.hasErrors()) { // 에러 처리
            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });
            throw new ValidCheckException(errorMap);
        }

        System.out.println("전처리");
        // 이 위로 전처리
        Object target = proceedingJoinPoint.proceed(); // 메서드의 body
        // 이 아래로 후처리
        System.out.println("후처리");

        return target;
    }
    
    
}
