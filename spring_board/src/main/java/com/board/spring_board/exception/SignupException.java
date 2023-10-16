package com.board.spring_board.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class SignupException extends RuntimeException {

    private Map<String, String> errorMap = new HashMap<>();

    public SignupException(Map<String, String> errorMap) {
        super("회원가입 오류");
        this.errorMap = errorMap;
        errorMap.forEach((k, v) -> {
            System.out.println(k+ ": " + v);
        });
    }

}
