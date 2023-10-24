package com.board.spring_board.service;

import com.board.spring_board.dto.MergeOauth2ReqDto;
import com.board.spring_board.entity.User;
import com.board.spring_board.exception.MismatchedPasswordException;
import com.board.spring_board.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupMergeService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public boolean updateMergeUserService(MergeOauth2ReqDto mergeOauth2ReqDto) {
        if (!passwordEncoder.matches(mergeOauth2ReqDto.getPassword(), userMapper.findUserByEmail(mergeOauth2ReqDto.getEmail()).getPassword())) {
            throw new MismatchedPasswordException();
        }
        return userMapper.updateOauth2AndProvider(mergeOauth2ReqDto.toUserEntity()) > 0;
    }
}
