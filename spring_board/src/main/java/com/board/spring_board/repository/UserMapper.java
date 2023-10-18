package com.board.spring_board.repository;

import com.board.spring_board.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public Integer saveUser(User user);
    public User findUserbyEmail(String email);
    public Integer checkSignupUnique(String email, String nickname);
}