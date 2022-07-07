package com.example.dec2021springboot.services;

import com.example.dec2021springboot.dao.PassportDAO;
import com.example.dec2021springboot.dao.UserDAO;
import com.example.dec2021springboot.models.Passport;
import com.example.dec2021springboot.models.User;
import com.example.dec2021springboot.models.dto.UserPassportRequestDTO;
import com.example.dec2021springboot.models.dto.UserPassportResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserDAO userDAO;
    private PassportDAO passportDAO;


    public void save(User user) {
        if (user.getName() != null) {
            userDAO.save(user);
        }
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public User findById(int id) {
        return (userDAO.findById(id).orElse(new User()));
    }

    public List<User> findByName(String name) {
        return userDAO.findByName(name);
    }

    public UserPassportResponseDTO convertUserDto(UserPassportRequestDTO requestDTO) {
        int user_id = requestDTO.getUser_id();
        int passport_id = requestDTO.getPassport_id();
        User user = userDAO.findById(user_id).get();
        Passport passport = passportDAO.findById(passport_id).get();
        user.setPassport(passport);
        userDAO.save(user);
        UserPassportResponseDTO responseDTO = new UserPassportResponseDTO(user);
        return responseDTO;
    }

}
