package com.project.isa.service.impl;

import com.project.isa.model.PromotionAdventure;
import com.project.isa.model.User;
import com.project.isa.request.UserRequest;
import com.project.isa.service.PromotionAdventureService;
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

    @Autowired
    PromotionAdventureService promotionAdventureService;

    @Async
    public void sendNotificationAsync(UserRequest userRequest, Long id) throws MailException, InterruptedException, MessagingException {
        System.out.println("Slanje emaila...");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

        String htmlMsg = "<h3>Hello</h3> <br> <p>to activate this account visit <a href=\"http://localhost:8080/api/activateacc/"+id.toString()+"\">link</a></p>";
        System.out.println(htmlMsg);
        mimeMessage.setContent(htmlMsg, "text/html");
        helper.setTo("admin@example.com");
        helper.setSubject("Verification");
        helper.setFrom(environment.getProperty("spring.mail.username"));
        javaMailSender.send(mimeMessage);
        System.out.println("Email poslat!");
    }

    public void deleteAccountAsync(String email, String reason) throws MailException, InterruptedException, MessagingException {
        System.out.println("Slanje emaila...");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

        String htmlMsg = "<h3>Hello</h3> <br> <p> your account is now deleted. Reason: <br>" + reason +"</p>";
        System.out.println(htmlMsg);
        mimeMessage.setContent(htmlMsg, "text/html");
        helper.setTo(email);
        helper.setSubject("Verification");
        helper.setFrom(environment.getProperty("spring.mail.username"));
        javaMailSender.send(mimeMessage);
        System.out.println("Email poslat!");
    }

    public void deleteDenied(String email, String reason) throws MailException, InterruptedException, MessagingException {
        System.out.println("Slanje emaila...");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

        String htmlMsg = "<h3>Hello</h3> <br> <p> you can not delete your account. Reason: <br>" + reason +"</p>";
        System.out.println(htmlMsg);
        mimeMessage.setContent(htmlMsg, "text/html");
        helper.setTo(email);
        helper.setSubject("Verification");
        helper.setFrom(environment.getProperty("spring.mail.username"));
        javaMailSender.send(mimeMessage);
        System.out.println("Email poslat!");
    }

    public void sendNewPromotionNotification(String email, Long id) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

        PromotionAdventure promotion = promotionAdventureService.findById(id);

        String htmlMsg = "<h3>Hello</h3><br> <p>New promotion arrived: <br>"+promotion.getDescription()+" </p>";
        System.out.println(htmlMsg);
        mimeMessage.setContent(htmlMsg, "text/html");
        helper.setTo(email);
        helper.setSubject("New promotion");
        helper.setFrom(environment.getProperty("spring.mail.username"));
        javaMailSender.send(mimeMessage);
        System.out.println("Email poslat!");
    }

}
