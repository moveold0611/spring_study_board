package com.board.spring_board.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ValidCheckException extends RuntimeException{
    private Map<String, String> errorMap = new HashMap<>();

    public ValidCheckException(Map<String, String> errorMap) {
        super("유효성 검사 오류");
        this.errorMap = errorMap;
        errorMap.forEach((k, v) -> {
            System.out.println(k+ ": " + v);
        });
    }
}
