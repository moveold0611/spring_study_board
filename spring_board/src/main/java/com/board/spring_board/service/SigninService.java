package com.board.spring_board.service;

import com.board.spring_board.exception.SigninException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SigninService {

    public boolean SigninService() {
        boolean success = true;
        if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Signin Service 오류 발생");
            throw new SigninException(errorMap);
        }
        return success;
    }
}
