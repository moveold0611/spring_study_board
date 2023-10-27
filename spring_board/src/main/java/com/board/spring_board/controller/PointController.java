package com.board.spring_board.controller;

import com.board.spring_board.aop.ReturnAop;
import com.board.spring_board.dto.PointUseReqDto;
import com.board.spring_board.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PointController {
    private final PointService pointService;

    @PostMapping("/usepoint")
    public ResponseEntity<?> pointUse(@RequestBody PointUseReqDto pointUseReqDto) {
        System.out.println("test2");
        return ResponseEntity.ok().body(pointService.pointUse(pointUseReqDto));
    }
}
