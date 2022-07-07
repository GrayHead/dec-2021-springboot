package com.example.dec2021springboot.models.dto;

import com.example.dec2021springboot.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPassportResponseDTO {
    private String username;
    private String passportSeries;

    public UserPassportResponseDTO(User user) {
        this.username = user.getName();
        this.passportSeries = user.getPassport().getSeries();
    }
}
