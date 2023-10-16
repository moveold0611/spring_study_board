package com.board.spring_board.controller;

import com.board.spring_board.dto.SigninReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SigninController {

    @CrossOrigin
    @PostMapping("/signin")
    public ResponseEntity<?> Signin(@RequestBody SigninReqDto signinReqDto) {
        return ResponseEntity.ok(200);
    }
}
