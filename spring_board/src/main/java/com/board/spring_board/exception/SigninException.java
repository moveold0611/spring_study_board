package com.board.spring_board.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class SigninException extends RuntimeException{

    private Map<String, String> errorMap = new HashMap<>();

    public SigninException(Map<String, String> errorMap) {
        super("로그인 오류");
        this.errorMap = errorMap;
        errorMap.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
    }
}
