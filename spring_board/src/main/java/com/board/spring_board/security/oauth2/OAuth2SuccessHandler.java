package com.board.spring_board.security.oauth2;

import com.board.spring_board.entity.User;
import com.board.spring_board.repository.UserMapper;
import com.board.spring_board.security.JwtTokenProvider;
import com.board.spring_board.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String oauth2Id = authentication.getName();
        User user = userMapper.findUserByOauth2Id(oauth2Id);


        // 회원가입이 안되었을 때 OAuth2 계정 회원가입 페이지로 이동
        if(user == null) {
            DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
            String name = defaultOAuth2User.getAttributes().get("name").toString();
            String profileImg = defaultOAuth2User.getAttributes().get("profile_image").toString();
            String provider = defaultOAuth2User.getAttributes().get("provider").toString();

            // 클라이언트로 다시 이동
            response.sendRedirect("http://localhost:3000/auth/signup/oauth2" +
                    "?oauth2Id=" + oauth2Id
                    + "&name=" + URLEncoder.encode(name, "UTF-8")
                    + "&profileImg=" + profileImg
                    + "&provider=" + provider);
        }

    }
}
