package com.board.spring_board.controller;


import com.board.spring_board.exception.SigninException;
import com.board.spring_board.exception.SignupException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @CrossOrigin
    @ExceptionHandler(SignupException.class)
    public ResponseEntity<?> signupHandlerException(SignupException signupException) {
        return ResponseEntity.badRequest().body(signupException.getErrorMap());
    }

    @CrossOrigin
    @ExceptionHandler(SigninException.class)
    public ResponseEntity<?> signinHandlerException(SigninException signinException) {
        return ResponseEntity.badRequest().body(signinException.getMessage());
    }
}
