package com.board.spring_board.service;

import com.board.spring_board.dto.UpdatePasswordReqDto;
import com.board.spring_board.dto.UpdateProfileImgReqDto;
import com.board.spring_board.entity.User;
import com.board.spring_board.exception.AuthMailException;
import com.board.spring_board.exception.MismatchedPasswordException;
import com.board.spring_board.repository.UserMapper;
import com.board.spring_board.security.JwtTokenProvider;
import com.board.spring_board.security.PrincipalUser;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;


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

    @Transactional(rollbackFor = Exception.class)
    public boolean updateProfileImgService(UpdateProfileImgReqDto updateProfileImgReqDto) {
        PrincipalUser principalUser =
                (PrincipalUser) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        return userMapper.updateProfileUrl(User.builder()
                .userId(principalUser.getUser().getUserId())
                .profileUrl(updateProfileImgReqDto.getProfileImgUrl())
                .build()) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updatePasswordService(UpdatePasswordReqDto updatePasswordReqDto) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication().getName();

        User user = userMapper.findUserByEmail(email);

        if(!Objects.equals(updatePasswordReqDto.getNewPassword(), updatePasswordReqDto.getCheckNewPassword())) {
            throw new MismatchedPasswordException();
        }

        if (!passwordEncoder.matches(updatePasswordReqDto.getOldPassword() ,user.getPassword())) {
            throw new BadCredentialsException("BadCredential");
        }

        return userMapper.updatePassword(User.builder()
                .email(email)
                .password(passwordEncoder.encode(updatePasswordReqDto.getNewPassword()))
                .build()) > 0;
    }
}
