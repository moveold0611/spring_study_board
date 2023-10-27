package com.board.spring_board.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class PrincipalRespDto {
    private int userId;
    private String email;
    private String name;
    private String nickname;
    private boolean enabled;
    private String profileUrl;
    private String oauth2Id;
    private String provider;
    private int userPoint;

}
