package com.board.spring_board.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SigninReqDto {
    @Email
    private String email;
    @NotBlank
    private String password;

}
