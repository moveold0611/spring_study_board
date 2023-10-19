package com.board.spring_board.controller;

import com.board.spring_board.aop.annotation.ValidAop;
import com.board.spring_board.dto.SigninReqDto;
import com.board.spring_board.entity.User;
import com.board.spring_board.exception.SigninException;
import com.board.spring_board.service.SigninService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SigninController {

    private final SigninService signinService;

    @ValidAop
    @PostMapping("/signin")
    public ResponseEntity<?> Signin(@Valid @RequestBody SigninReqDto signinReqDto, BindingResult bindingResult) {
        System.out.println("test1");

        String token =  signinService.SigninService(signinReqDto);

        if(token == null) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "로그인 오류");
            throw new SigninException(errorMap);
        }
        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/token/authenticate")
    public ResponseEntity<?> authenticate(@RequestHeader(value = "Authorization") String token) {

        return ResponseEntity.ok(200);
    }
}
