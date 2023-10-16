package com.board.spring_board.service;

import com.board.spring_board.dto.SignupReqDto;
import com.board.spring_board.exception.SignupException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SignupService {


    public void SignupService(SignupReqDto signupReqDto) {
        boolean success = true;

        if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Signup Service 오류 발생");
            throw new SignupException(errorMap);
        }
    }

}
