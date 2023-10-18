package com.board.spring_board.security;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class PrincipalEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException auth) throws IOException, ServletException {
        resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", getErrorMessage(auth));
        JsonMapper jsonMapper = new JsonMapper();
        String errorJsonMessage = jsonMapper.writeValueAsString(errorMap);

        resp.getWriter().println(errorJsonMessage);
    }

    private String getErrorMessage(AuthenticationException authException) {
        if(authException.getClass() == InternalAuthenticationServiceException.class) {
            return "잘못된 사용자 정보입니다. 다시 확인하세요.";
        }else if(authException.getClass() == BadCredentialsException.class) {
            return "잘못된 사용자 정보입니다. 다시 확인하세요.";
        }else if(authException.getClass() == CredentialsExpiredException.class) {
            return "인증서가 만료되었습니다. 관리자에게 문의하세요.";
        }else if(authException.getClass() == BadCredentialsException.class) {
            return "비밀번호 오류.";
        }else {
            return "사용자 정보 오류.";
        }
    }

}
