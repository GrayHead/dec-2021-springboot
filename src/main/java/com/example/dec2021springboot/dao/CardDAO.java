package com.example.dec2021springboot.dao;

import com.example.dec2021springboot.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDAO extends JpaRepository<Card, Integer> {
}
