package com.ecommerce.shubkart.service;

import com.ecommerce.shubkart.services.EmailServices.EmailService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;


    @Test
    void testSendMail() {
        emailService.sendEmail("shubhamshejaval@gmail.com",
                "Testing Java mail sender",
                "Hi, aap kaise hain ?");
    }
}
