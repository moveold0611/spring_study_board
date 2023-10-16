package com.board.spring_board.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class BoardException extends RuntimeException{

    private Map<String, String> errorMap = new HashMap<>();

    public BoardException(Map<String, String> errorMap) {
        super("Board 오류");
        this.errorMap = errorMap;
        errorMap.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
    }

}

