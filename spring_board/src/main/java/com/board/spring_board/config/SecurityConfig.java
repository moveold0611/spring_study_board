package com.board.spring_board.config;

import com.board.spring_board.filter.JwtAuthenticationFilter;
import com.board.spring_board.security.PrincipalEntryPoint;
import com.board.spring_board.security.oauth2.OAuth2SuccessHandler;
import com.board.spring_board.service.PrincipalDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PrincipalEntryPoint principalEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final PrincipalDetailService principalDetailService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/board/content", "/board/like/**")
                .authenticated()
                .antMatchers("/auth/**", "/board/**", "/boards/**", "/products")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(principalEntryPoint)
                .and()
                .oauth2Login()
                .loginPage("http://localhost:3000/auth/signin")
                .successHandler(oAuth2SuccessHandler)
                .userInfoEndpoint()
                .userService(principalDetailService);
    }

    // Bean = 외부 라이브러리 객체를 상속하여 Override(재정의)
    @Bean // Configuration 어노테이션이 있는 클래스에 Bean 사용 가능
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }





}
