package com.board.spring_board.service;

import com.board.spring_board.entity.User;
import com.board.spring_board.repository.UserMapper;
import com.board.spring_board.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userMapper.findUserbyEmail(email);
        if(user != null) {
            return new PrincipalUser(user);
        }
        return null;
    }
}
