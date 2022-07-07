package com.example.dec2021springboot.controllers.exceptionHandlers;

import com.example.dec2021springboot.models.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> errorA(MethodArgumentNotValidException exception) {
        System.out.println(exception);
        return new ResponseEntity<>(new ErrorDTO(exception, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

}
