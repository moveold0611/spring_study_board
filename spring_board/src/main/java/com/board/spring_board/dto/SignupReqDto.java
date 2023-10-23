package com.board.spring_board.dto;

import com.board.spring_board.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SignupReqDto {

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String nickname;

    private String oauth2Id;
    private String profileUrl;
    private String provider;

    public User toEntityBySignupReqDto(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .nickname(nickname)
                .oauth2Id(oauth2Id)
                .profileUrl(profileUrl)
                .provider(provider)
                .build();
    }
}
