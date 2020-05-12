package com.teamrocket.projetdevop.ivvqproject.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        try {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));
            String testPort = "Hello" + dbUri.toString();
            return testPort;
        } catch (URISyntaxException exception) {
            return "Greetings from Spring Boot!";
        }
    }

}