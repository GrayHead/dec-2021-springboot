package com.example.dec2021springboot.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPassportRequestDTO {

    private int user_id;
    private int passport_id;
}
