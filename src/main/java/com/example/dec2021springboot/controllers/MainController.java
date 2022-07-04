package com.example.dec2021springboot.controllers;

import com.example.dec2021springboot.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {


    ArrayList<User> users = new ArrayList<>();


    public MainController() {
        users.add(new User(2, "kokos"));
        users.add(new User(1, "ananas"));
        users.add(new User(3, "banan"));
        users.add(new User(5, "tomat"));
        users.add(new User(4, "potatos"));
        users.add(new User(6, "mango"));

    }

//    @GetMapping("/")
//    public void foobar() {
//        System.out.println("hi");
//    }
//
//    @GetMapping("/hello")
//    public ResponseEntity<String> hello() {
//        ResponseEntity<String> stringResponseEntity = new ResponseEntity<String>("this is body response", HttpStatus.OK);
//        return stringResponseEntity;
//    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return users;
    }


//    @PostMapping("/users")
//    public ResponseEntity<List<User>> saveUser(@RequestBody User user) {
//        users.add(user);
//        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
//
//    }

    @PostMapping("/users")
    public ResponseEntity<List<User>> saveUser(@RequestParam int id, @RequestParam String name) {
        users.add(new User(id, name));
        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User u = users.stream().filter(user -> user.getId() == id).collect(Collectors.toList()).get(0);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        users.removeIf(next -> next.getId() == id);

    }


}
