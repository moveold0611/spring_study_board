package com.board.spring_board.service;

import com.board.spring_board.repository.UserMapper;
import com.board.spring_board.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    private final JwtTokenProvider jwtTokenProvider;

    public boolean sendAuthMail() {
        String toEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        String token = jwtTokenProvider.generateAuthMailToken(toEmail);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setSubject("스프링 부트 사용자 인증 메일 테스트"); // 메일 제목
            helper.setFrom("b44sup97045@gmail.com"); // 보내는 사람
            helper.setTo(toEmail); // 받는 사람 이메일

            mimeMessage.setText( // 내용 설정
                    "<div>\n" +
            "            <h1>사용자 인증 메일</h1>\n" +
            "            <p>사용자 인증을 완료하려면 아래의 버튼을 클리하세요.</p>\n" +
            "            <a href=\"http://localhost:8080/auth/mail?token="+ token +"\">인증하기</a>\n" +
            "        </div>", "utf-8" // 인코딩 설정
                    ,"html" // 형식 설정
            );

            javaMailSender.send(mimeMessage);
        }catch (Exception e) {
            return false;
        }
        return true;
    }


}
