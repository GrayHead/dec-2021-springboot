package com.example.dec2021springboot.controllers;

import com.example.dec2021springboot.dao.CustomerDAO;
import com.example.dec2021springboot.models.Customer;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MainController {
    private CustomerDAO customerDAO;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home() {
        return "asfdhgad";
    }

    @PostMapping("/")
    public String homePost() {
        return "homePost";
    }

    @GetMapping("/customers")
    public String users() {
        return "customers array";
    }

    @PostMapping("/customers")
    public void saveUser(@RequestBody Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerDAO.save(customer);
    }
}
