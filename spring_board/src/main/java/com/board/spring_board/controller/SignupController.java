package com.board.spring_board.controller;

import com.board.spring_board.aop.annotation.ArgsAop;

import com.board.spring_board.aop.annotation.ReturnAop;
import com.board.spring_board.aop.annotation.TimeAop;
import com.board.spring_board.aop.annotation.ValidAop;
import com.board.spring_board.dto.SignupReqDto;
import com.board.spring_board.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SignupController {
    private final SignupService signupService;

    @ArgsAop
    @ValidAop
    @ReturnAop
    @PostMapping("/signup")
    public ResponseEntity<?> Signup(@Valid @RequestBody SignupReqDto signupReqDto, BindingResult bindingResult) {

        System.out.println("Signup Controller");
        System.out.println(signupReqDto.getEmail());
        return ResponseEntity.ok(200);
    }
}
