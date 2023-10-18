package com.board.spring_board.security;

import com.board.spring_board.entity.User;
import com.board.spring_board.repository.UserMapper;
import com.board.spring_board.service.PrincipalDetailService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtTokenProvider {
    private final Key key;
    private final PrincipalDetailService principalDetailService;
    private final UserMapper userMapper;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret,
                            @Autowired PrincipalDetailService principalDetailService,
                            @Autowired UserMapper userMapper) {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.userMapper = userMapper;
        this.principalDetailService = principalDetailService;
    }

    public String generateAccessToken(Authentication authentication) {
        String email = authentication.getName();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boolean enabled = userDetails.isEnabled();

        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject("AccessToken")
                .claim("enabled", enabled)
                .signWith(key, SignatureAlgorithm.HS256);

        User user = userMapper.findUserbyEmail(email);
        if(user != null) {
            return jwtBuilder.claim("email", user.getEmail()).compact();
        }
        return null;
    }


    public Claims getClaims(String token){
        Claims claims = null;
        try {
           claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }


    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        if(claims == null) {
            return null;
        }
        User user = userMapper.findUserbyEmail(claims.get("email").toString());
        if (user == null) {
            return null;
        }
        PrincipalUser principalUser = new PrincipalUser(user);
        return new UsernamePasswordAuthenticationToken(principalUser, null, principalUser.getAuthorities());
    }


}
