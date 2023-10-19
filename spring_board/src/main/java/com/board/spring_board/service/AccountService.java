package com.board.spring_board.service;

import com.board.spring_board.entity.User;
import com.board.spring_board.exception.AuthMailException;
import com.board.spring_board.repository.UserMapper;
import com.board.spring_board.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;

    public String AccountService() {
        String userInfo = null;

        return userInfo;
    }

    public boolean isSuccessedAuthEmailService(String token) {
        Claims claims = jwtTokenProvider.getClaims(token);

        System.out.println("test acc");
        if(claims == null){
            throw new AuthMailException("만료된 인증 요청입니다.");
        }

        String email = claims.get("email").toString();
        User user = userMapper.findUserByEmail(email);
        if(user.getEnabled() > 0) {
            throw new AuthMailException("이미 인증된 사용자입니다.");
        }

        boolean success = userMapper.updateEnabled(email) > 0;
        System.out.println("Service test " + success);
        return success;
    }

}
