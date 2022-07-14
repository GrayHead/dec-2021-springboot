package com.example.dec2021springboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String home() {
        return "asfdhgad";
    }

    @PostMapping("/")
    public String homePost () {
            return "homePost";
    }
}
