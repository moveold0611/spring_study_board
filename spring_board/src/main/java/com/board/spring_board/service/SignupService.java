package com.board.spring_board.service;

import com.board.spring_board.dto.SignupReqDto;
import com.board.spring_board.entity.User;
import com.board.spring_board.exception.SignupException;
import com.board.spring_board.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = SignupException.class)
    public boolean SignupService(SignupReqDto signupReqDto) {
        System.out.println("service");
        System.out.println(signupReqDto.getPassword());

        User user = User.builder()
                .email(signupReqDto.getEmail())
                .password(passwordEncoder.encode(signupReqDto.getPassword()))
                .name(signupReqDto.getName())
                .nickname(signupReqDto.getNickname())
                .build();

        System.out.println(user);

        boolean success = userMapper.saveUser(user) > 0;

        if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Signup Service 오류 발생");
            throw new SignupException(errorMap);
        }
        return success;
    }
}
