package com.example.dec2021springboot.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Data
@NoArgsConstructor
public class ErrorDTO {

    private String msg;
    private int code;

    private String field;

    public ErrorDTO(MethodArgumentNotValidException exception, int code) {
        this.code = code;
        this.msg = exception.getFieldError().getDefaultMessage();
        this.field = exception.getFieldError().getField();

    }
}
