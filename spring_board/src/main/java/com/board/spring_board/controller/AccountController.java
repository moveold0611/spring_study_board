package com.board.spring_board.controller;

import com.board.spring_board.dto.PrincipalRespDto;
import com.board.spring_board.entity.User;
import com.board.spring_board.repository.UserMapper;
import com.board.spring_board.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final UserMapper userMapper;

    @GetMapping("/account/principal")
    public ResponseEntity<?> getPrincipal() {
        System.out.println("Account Controller 진입");
        PrincipalUser principalUser
                = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = principalUser.getUser();

        PrincipalRespDto principalRespDto = user.toPrincipalRespDtoByUser();

        return  ResponseEntity.ok().body(principalRespDto);
    }


}
