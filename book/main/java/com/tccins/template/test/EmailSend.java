package com.tccins.template.test;

import java.io.IOException;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public class EmailSend {
	
	  //반드시 Autowired로 @RequiredArgsConstructor를 수입해서 final 사용하면 에러 발생
	  @Autowired
	  private JavaMailSender javaMailSender;

	  @Autowired
	  private SpringTemplateEngine templateEngine;
	
	
	
	 /**
     * 이메일 발송 함수
     * @param title 이메일 제목
     * @param to 받는 사람
     * @param templateName 이메일 템플릿
     * @param values 이메일에 들어가는 값
     * @throws MessagingException
     * @throws IOException
     */
    public void send(String title, String to, String templateName, HashMap<String, String> values) throws MessagingException, IOException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        //메일 제목 설정
        helper.setSubject(title);

        //수신자 설정
        helper.setTo(to);

        //템플릿에 전달할 데이터 설정
        Context context = new Context();
        values.forEach((key, value)->{
            context.setVariable(key, value);
        });

        //메일 내용 설정 : 템플릿 프로세스
        String html = templateEngine.process(templateName, context);
        helper.setText(html, true);

        //메일 보내기
        javaMailSender.send(message);
    }

}
