package com.project.isa.service.impl;

import com.project.isa.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    Environment environment;

    @Autowired
    UserServiceImpl userService;

    @Async
    public void sendNotificaitionAsync(UserRequest userRequest, Long id) throws MailException, InterruptedException, MessagingException {
        System.out.println("Slanje emaila...");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

        String htmlMsg = "<h3>Hello "+userRequest.getFirstname()+"</h3><br> <p>to activate Your account visit <a href=\"http://localhost:8080/api/activateacc/"+id.toString()+"\">link</a></p>";
        System.out.println(htmlMsg);
        mimeMessage.setContent(htmlMsg, "text/html");
        helper.setTo(userRequest.getEmail());
        helper.setSubject("Verification");
        helper.setFrom(environment.getProperty("spring.mail.username"));
        javaMailSender.send(mimeMessage);
        System.out.println("Email poslat!");
    }

}
