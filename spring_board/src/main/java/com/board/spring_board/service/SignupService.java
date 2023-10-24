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
        User user = signupReqDto.toEntityBySignupReqDto(passwordEncoder);

        System.out.println(user);
        Integer uniqueCheck = userMapper.checkSignupUnique(user.getEmail(), user.getNickname());

        switch (uniqueCheck) {
            case 1:
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("email", "email 중복");
                throw new SignupException(errorMap);

            case 2:
                Map<String, String> errorMap2 = new HashMap<>();
                errorMap2.put("nickname", "nickname 중복");
                throw new SignupException(errorMap2);

            case 3:
                Map<String, String> errorMap3 = new HashMap<>();
                errorMap3.put("message", "email, nickname 중복");
                throw new SignupException(errorMap3);
        }

        boolean success = userMapper.saveUser(user) > 0;
        if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Signup Service 오류 발생");
            throw new SignupException(errorMap);
        }

        return success;
    }
}
