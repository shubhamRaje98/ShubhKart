package com.ecommerce.shubkart.services.EmailServices;


import com.ecommerce.shubkart.models.EmailKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailKafkaConsumer {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "my-topic", groupId = "console-consumer-31151")
    public void consume(EmailKafka emailKafka) {
        sendEmail(emailKafka);
    }

    private void sendEmail(EmailKafka emailKafka) {
        emailService.sendEmail(emailKafka.getEmail(), "Sentiment for previous week", emailKafka.getMessage());
    }
}
