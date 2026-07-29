package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/*")
    public String greeting() {
        return "Chào mừng bạn đến với thế giới của Spring Boot 3!";
    }

    @GetMapping("/hello")
    public String greetingHello(@RequestParam String name){
        return "Xin chào, " + name;
    }
}
