package com.board.spring_board.entity;


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
    private int enabled; // default = 0
}
