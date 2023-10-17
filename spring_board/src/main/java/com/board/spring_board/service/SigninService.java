package com.board.spring_board.service;

import com.board.spring_board.dto.SigninReqDto;
import com.board.spring_board.entity.User;
import com.board.spring_board.exception.SigninException;
import com.board.spring_board.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SigninService {

    private final UserMapper userMapper;

    public User SigninService(SigninReqDto signinReqDto) {
        User user;
        try {
            user = userMapper.findUserbyEmail(signinReqDto.getEmail());
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Signin Service 오류 발생");
            throw new SigninException(errorMap);
        }
        return user;
    }
}
