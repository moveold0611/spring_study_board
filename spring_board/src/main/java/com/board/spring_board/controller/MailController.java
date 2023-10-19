package com.board.spring_board.controller;

import com.board.spring_board.service.AccountService;
import com.board.spring_board.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;
    private final AccountService accountService;

    @PostMapping("/account/mail/auth")
    public ResponseEntity<?> sendAuthenticationMail() {
        return ResponseEntity.ok().body(mailService.sendAuthMail());
    }

    @GetMapping("/auth/mail")
    public ResponseEntity<?> authenticateEmail(String token) {
        System.out.println("testttest");
        accountService.isSuccessedAuthEmailService(token);
        return ResponseEntity.ok().body(true);
    }
}
