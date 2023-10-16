package com.board.spring_board.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 메소드 위에 달 수 있는
@Retention(RetentionPolicy.RUNTIME) // 실행중에 읽는
public @interface ValidAop { // 어노테이션
}
