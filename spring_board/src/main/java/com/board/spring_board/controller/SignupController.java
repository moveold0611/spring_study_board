package com.board.spring_board.controller;

import com.board.spring_board.dto.SignupReqDto;
import com.board.spring_board.exception.SignupException;
import com.board.spring_board.service.SignupService;
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
public class SignupController {
    private final SignupService signupService;

    @CrossOrigin
    @PostMapping("/signup")
    public ResponseEntity<?> Signup(@RequestBody SignupReqDto signupReqDto) {

        System.out.println("Signup Controller");

//        if(bindingResult.hasErrors()) {
//            Map<String, String> errorMap = new HashMap<>();
//            bindingResult.getFieldErrors().forEach(error -> {
//                errorMap.put(error.getField(), error.getDefaultMessage());
//            });
//            throw new SignupException(errorMap);
//        }

        System.out.println(signupReqDto.getEmail());
        return ResponseEntity.ok(200);
    }
}
