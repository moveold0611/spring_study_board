package com.board.spring_board.exception;


import java.util.HashMap;
import java.util.Map;

public class AuthMailException extends RuntimeException{
    private Map<String, String> errorMap = new HashMap<>();

    public AuthMailException(String message) {
        super("인증메일 토큰 유효성 검사 오류");
    }


}
