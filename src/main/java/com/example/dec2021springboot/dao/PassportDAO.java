package com.example.dec2021springboot.dao;

import com.example.dec2021springboot.models.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportDAO extends JpaRepository<Passport, Integer> {
}
