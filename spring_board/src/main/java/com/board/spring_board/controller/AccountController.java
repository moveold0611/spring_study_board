package com.board.spring_board.controller;

import com.board.spring_board.aop.annotation.ArgsAop;
import com.board.spring_board.aop.annotation.ValidAop;
import com.board.spring_board.dto.PrincipalRespDto;
import com.board.spring_board.dto.UpdatePasswordReqDto;
import com.board.spring_board.dto.UpdateProfileImgReqDto;
import com.board.spring_board.entity.User;
import com.board.spring_board.repository.UserMapper;
import com.board.spring_board.security.PrincipalUser;
import com.board.spring_board.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final UserMapper userMapper;
    private final AccountService accountService;


    @GetMapping("/account/principal")
    public ResponseEntity<?> getPrincipal() {
        System.out.println("Account Controller 진입");
        PrincipalUser principalUser
                = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = principalUser.getUser();

        PrincipalRespDto principalRespDto = user.toPrincipalRespDtoByUser();

        return  ResponseEntity.ok().body(principalRespDto);
    }

    @PutMapping("/account/profile/img")
    public ResponseEntity<?> updateProfileImg(@RequestBody UpdateProfileImgReqDto updateProfileImgReqDto) {
        return ResponseEntity.ok().body(accountService.updateProfileImgService(updateProfileImgReqDto));
    }

    @ArgsAop
    @ValidAop
    @PutMapping("/account/password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody  UpdatePasswordReqDto updatePasswordReqDto, BindingResult bindingResult) {
        System.out.println(updatePasswordReqDto);
        return  ResponseEntity.ok().body(accountService.updatePasswordService(updatePasswordReqDto));
    }
}
