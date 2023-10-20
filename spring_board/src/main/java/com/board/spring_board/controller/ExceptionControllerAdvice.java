package com.board.spring_board.controller;



import com.board.spring_board.exception.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ExceptionControllerAdvice {


    @ExceptionHandler(SignupException.class)
    public ResponseEntity<?> signupHandlerException(SignupException signupException) {
        return ResponseEntity.badRequest().body(signupException.getErrorMap());
    }

    @ExceptionHandler(MismatchedPasswordException.class)
    public ResponseEntity<?> mismatchedPasswordException(MismatchedPasswordException mismatchedPasswordException) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("mismatched", mismatchedPasswordException.getMessage());
        return ResponseEntity.badRequest().body(errorMap);
    }

    @ExceptionHandler(SigninException.class)
    public ResponseEntity<?> signinHandlerException(SigninException signinException) {
        return ResponseEntity.badRequest().body(signinException.getErrorMap());
    }

    @ExceptionHandler(AuthMailException.class)
    public ResponseEntity<?> mailException(AuthMailException authMailException) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("authMail", authMailException.getMessage());
        return ResponseEntity.ok().body(errorMap);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> usernameNotFoundException(UsernameNotFoundException usernameNotFoundException) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("authError", "사용자 정보를 확인해주세요.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMap);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> badCredentialsException(BadCredentialsException badCredentialsException) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("authError", "사용자 정보를 확인해주세요.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMap);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<?> disabledException(DisabledException disabledException) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("disabled", "이메일 인증이 필요합니다.");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMap);
    }

    @ExceptionHandler(JwtException .class)
    public ResponseEntity<?> jwtException(JwtException jwtException) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("jwt", "유효하지 않은 토큰입니다.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMap);
    }

    @ExceptionHandler(BoardException.class)
    public ResponseEntity<?> boardHandlerException(BoardException boardException) {
        return ResponseEntity.badRequest().body(boardException.getErrorMap());
    }

    @ExceptionHandler(ValidCheckException.class)
    public ResponseEntity<?> ValidHandlerException(ValidCheckException validCheckException) {
        System.out.println("Valid Handler 에러");
        return ResponseEntity.badRequest().body(validCheckException.getErrorMap());
    }
}
