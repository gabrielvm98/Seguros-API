package com.example.seguroscrud.exceptions;

import com.example.seguroscrud.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class NotFoundException extends GeneralException{

    public NotFoundException(String code, String message) {
        super(code, HttpStatus.NOT_FOUND.value(), message);
    }

    public NotFoundException(String code, String message, ErrorDTO data) {
        super(code, HttpStatus.NOT_FOUND.value(), message, Arrays.asList(data));
    }
}