package com.board.spring_board.security;

import com.board.spring_board.entity.User;
import com.board.spring_board.repository.UserMapper;
import com.board.spring_board.service.PrincipalDetailService;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.Signature;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final Key key;
    private final PrincipalDetailService principalDetailService;
    private final UserMapper userMapper;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret,
                            @Autowired PrincipalDetailService principalDetailService,
                            @Autowired UserMapper userMapper) {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.principalDetailService = principalDetailService;
        this.userMapper = userMapper;
    }

    public String generateAccessToken(Authentication authentication) {
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        Date tokenExpiresDate = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));
        JwtBuilder jwtBuilder = Jwts.builder()
                .claim("username", principalUser.getUsername())
                .claim("auth", principalUser.getAuthorities())
                .setExpiration(tokenExpiresDate)
                .signWith(key, SignatureAlgorithm.HS256);

        User user = userMapper.findUserbyEmail(principalUser.getUsername());
        if(user != null) {
            return jwtBuilder.claim("username", user.getEmail()).compact();
        }
        return null;
    }


}
