package org.example.testspringcsdl.service.impl;

import org.example.testspringcsdl.dto.request.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(EmailRequest emailRequest){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("wonwon5800@gmail.com");
        simpleMailMessage.setTo(emailRequest.getToEmail());
        simpleMailMessage.setText(emailRequest.getBody());
        simpleMailMessage.setSubject(emailRequest.getSubject());

        javaMailSender.send(simpleMailMessage);

        System.out.println("success");

    }
}
