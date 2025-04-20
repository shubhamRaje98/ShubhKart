package com.ecommerce.shubkart.controllers;

import com.ecommerce.shubkart.models.EmailKafka;
import com.ecommerce.shubkart.models.User;
import com.ecommerce.shubkart.services.EmailServices.EmailService;
import com.ecommerce.shubkart.services.userService.UserService;
import com.ecommerce.shubkart.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/public")
public class PublicController {

    private UserService userService;
    private UserDetailsService userDetailsService;

    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    EmailService emailService;

    @Autowired
    private KafkaTemplate<String, EmailKafka> kafkaTemplate;

    public PublicController(UserService userService, UserDetailsService userDetailsService, JwtUtil jwtUtil){
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }
    @GetMapping("/check")
    public String check(){
        emailService.sendEmail("shubhamshejaval@gmail.com", "Testing JavaMail", "Get over with it!");
        return "Its up";
    }

    @PostMapping("/signup")
    public void signup(@RequestBody User user){
        EmailKafka emailKafka = EmailKafka.builder().email(user.getEmail()).message("Welcome and thanks for using ShubhKart! dear "+user.getUsername()).build();
        kafkaTemplate.send("my-topic", emailKafka);
        userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occurred while createAuthToken ", e);
            return new ResponseEntity<>("Incorrect username and password", HttpStatus.BAD_REQUEST);
        }
    }
}
