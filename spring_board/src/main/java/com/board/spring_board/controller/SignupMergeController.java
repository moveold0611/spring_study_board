package com.board.spring_board.controller;

import com.board.spring_board.aop.annotation.ValidAop;
import com.board.spring_board.dto.MergeOauth2ReqDto;
import com.board.spring_board.service.SignupMergeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SignupMergeController {
    private final SignupMergeService signupMergeService;

    @ValidAop
    @PutMapping("/auth/oauth2/merge")
    public ResponseEntity<?> oauth2Merge(@Valid @RequestBody MergeOauth2ReqDto mergeOauth2ReqDto, BindingResult bindingResult) {
        System.out.println("test");
        return ResponseEntity.ok().body(signupMergeService.updateMergeUserService(mergeOauth2ReqDto));
    }
}
