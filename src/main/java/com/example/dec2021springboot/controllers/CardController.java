package com.example.dec2021springboot.controllers;

import com.example.dec2021springboot.dao.CardDAO;
import com.example.dec2021springboot.models.Card;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cards")
public class CardController {


    private CardDAO cardDAO;

    @GetMapping("")
    public ResponseEntity<List<Card>> getCards() {
        return new ResponseEntity<>(cardDAO.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Card> getCard(@PathVariable int id) {
        return new ResponseEntity<>(cardDAO.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void saveCard(@RequestBody Card passport) {
        cardDAO.save(passport);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCard(@PathVariable int id) {
        cardDAO.deleteById(id);
    }

    @PatchMapping("")
    public ResponseEntity<Card> patchCard(@RequestBody Card passport) {

        Card save = cardDAO.save(passport);
        return new ResponseEntity<>(save, HttpStatus.ACCEPTED);
    }


}
