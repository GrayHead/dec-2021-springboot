package com.example.dec2021springboot.controllers;

import com.example.dec2021springboot.models.User;
import com.example.dec2021springboot.models.dto.UserPassportRequestDTO;
import com.example.dec2021springboot.models.dto.UserPassportResponseDTO;
import com.example.dec2021springboot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserService userService;


    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void saveUser(@RequestBody @Valid User user) {
        userService.save(user);

    }

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/byname")
    public ResponseEntity<List<User>> getUsersByName(@RequestParam String name) {
//        List<User> users = userDAO.findMeAUserWithName(name);
        List<User> users = userService.findByName(name);
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

//    @PatchMapping("")
//    public ResponseEntity<User> updateUser(@RequestBody User user) { // 1 name kokos
//        User save = userDAO.save(user);
//        return new ResponseEntity<>(save, HttpStatus.ACCEPTED);
//    }

    @PatchMapping("")
    public ResponseEntity<UserPassportResponseDTO> updateUser(@RequestBody UserPassportRequestDTO dto) {
        return new ResponseEntity<>(userService.convertUserDto(dto), HttpStatus.OK);
    }


    @GetMapping("/activateAccount/{id}")
    public ResponseEntity<String> activateAccount(@PathVariable int id) {
        return userService.activateUserAccount(id);

    }

}
