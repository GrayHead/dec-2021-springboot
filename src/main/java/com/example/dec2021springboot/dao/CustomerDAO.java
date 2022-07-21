package com.example.dec2021springboot.dao;

import com.example.dec2021springboot.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {
    Customer findByLogin(String login);
}
