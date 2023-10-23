package com.board.spring_board.repository;

import com.board.spring_board.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public Integer saveUser(User user);
    public User findUserByEmail(String email);
    public Integer checkSignupUnique(String email, String nickname);
    public Integer updateEnabled(String email);
    public Integer updateProfileUrl(User user);
    public Integer updatePassword(User user);
    public User findUserByOauth2Id(String oauth2Id);
    public User saveUserOfOauth2(User user);
}