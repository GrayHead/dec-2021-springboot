package com.example.dec2021springboot.controllers;

import com.example.dec2021springboot.dao.UserDAO;
import com.example.dec2021springboot.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserDAO userDAO;


    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void saveUser(@RequestBody User user) {
        userDAO.save(user);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userDAO.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userDAO.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping("/byname")
    public ResponseEntity<List<User>> getUsersByName(@RequestParam String name) {
//        List<User> users = userDAO.findMeAUserWithName(name);
        List<User> users = userDAO.findByName(name);
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @PatchMapping("")
    public ResponseEntity<User> updateUser(@RequestBody User user) { // 1 name kokos
        User save = userDAO.save(user);
        return new ResponseEntity<>(save, HttpStatus.ACCEPTED);
    }


}
