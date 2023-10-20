package com.board.spring_board.entity;


import com.board.spring_board.dto.PrincipalRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    private int userId;;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String profileUrl;
    private int enabled; // default = 0

    public PrincipalRespDto toPrincipalRespDtoByUser() {
        return PrincipalRespDto.builder()
                .userId(userId)
                .email(email)
                .name(name)
                .nickname(nickname)
                .enabled(enabled > 0)
                .profileUrl(profileUrl)
                .build();
    }
}
