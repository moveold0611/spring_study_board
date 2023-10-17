package com.board.spring_board.controller;

import com.board.spring_board.dto.SigninReqDto;
import com.board.spring_board.entity.User;
import com.board.spring_board.service.SigninService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SigninController {

    private final SigninService signinService;

    @PostMapping("/signin")
    public ResponseEntity<?> Signin(@RequestBody SigninReqDto signinReqDto) {
        User user =  signinService.SigninService(signinReqDto);

        return ResponseEntity.ok().body(user);
    }
}
