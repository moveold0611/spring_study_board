package com.board.spring_board.service;

import com.board.spring_board.dto.SigninReqDto;
import com.board.spring_board.entity.User;
import com.board.spring_board.exception.SigninException;
import com.board.spring_board.repository.UserMapper;
import com.board.spring_board.security.JwtTokenProvider;
import com.board.spring_board.security.PrincipalProvider;
import com.board.spring_board.security.PrincipalUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SigninService {

    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PrincipalProvider principalProvider;

    public String SigninService(SigninReqDto signinReqDto) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken
                    (signinReqDto.getEmail(), signinReqDto.getPassword());

        Authentication authentication = principalProvider.authenticate(authenticationToken);

        String accessToken = jwtTokenProvider.generateAccessToken(authentication);

        if (accessToken == null) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Signin Service 오류 발생");
            throw new SigninException(errorMap);
        }
        return accessToken;
    }


    public boolean authenticate(String token) {
        Claims claims = jwtTokenProvider.getClaims(token);

        if(claims == null) {
            throw new JwtException("토큰 유효성 검사 오류");
        }

        return Boolean.parseBoolean(claims.get("enabled").toString());

    }

}
