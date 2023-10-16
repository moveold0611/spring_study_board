package com.board.spring_board.controller;



import com.board.spring_board.exception.BoardException;
import com.board.spring_board.exception.SigninException;
import com.board.spring_board.exception.SignupException;
import com.board.spring_board.exception.ValidCheckException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionControllerAdvice {


    @ExceptionHandler(SignupException.class)
    public ResponseEntity<?> signupHandlerException(SignupException signupException) {
        System.out.println("Signup Handler 에러");
        return ResponseEntity.badRequest().body(signupException.getErrorMap());
    }


    @ExceptionHandler(SigninException.class)
    public ResponseEntity<?> signinHandlerException(SigninException signinException) {
        return ResponseEntity.badRequest().body(signinException.getMessage());
    }


    @ExceptionHandler(BoardException.class)
    public ResponseEntity<?> boardHandlerException(BoardException boardException) {
        return ResponseEntity.badRequest().body(boardException.getMessage());
    }

    @ExceptionHandler(ValidCheckException.class)
    public ResponseEntity<?> ValidHandlerException(ValidCheckException validCheckException) {
        System.out.println("Valid Handler 에러");
        return ResponseEntity.badRequest().body(validCheckException.getMessage());
    }
}
