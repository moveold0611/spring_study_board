package com.board.spring_board.controller;

import com.board.spring_board.aop.annotation.ValidAop;
import com.board.spring_board.dto.RegisterBoardReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BoardController {

    @ValidAop
    @PostMapping("/board/{category}")
    public ResponseEntity<?> register(@Valid @PathVariable RegisterBoardReqDto registerBoardReqDto, BindingResult bindingResult) {

        return ResponseEntity.ok(200);
    }
}
