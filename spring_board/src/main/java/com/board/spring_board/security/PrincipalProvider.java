package com.board.spring_board.security;

import com.board.spring_board.service.PrincipalDetailService;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.protocol.Protocol;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrincipalProvider implements AuthenticationProvider {

    private final PrincipalDetailService principalDetailService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails principalUser = principalDetailService.loadUserByUsername(email);
        if (!passwordEncoder.matches(password, principalUser.getPassword())) {
            throw new BadCredentialsException("BadCredentials");
        }
        return new UsernamePasswordAuthenticationToken(principalUser, password, principalUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
