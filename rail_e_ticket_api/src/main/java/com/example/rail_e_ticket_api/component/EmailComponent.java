package com.example.rail_e_ticket_api.component;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import static com.example.rail_e_ticket_api.util.interfaces.Url.*;

@Component
@RequiredArgsConstructor
public class EmailComponent {


    private final JavaMailSender javaMailSender;

    public void sendToRegistration(String mail) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mail);
            message.setTo("bekzod.m070@gmail.com");
            message.setSubject(mail + " want to join your team");
            message.setText(URL_GLOBAL_FRONT + URL_AUTH + "/verify/" + mail);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
