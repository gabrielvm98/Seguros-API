package com.example.seguroscrud.exceptions;

import com.example.seguroscrud.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class InternalServerErrorException extends GeneralException{
    public InternalServerErrorException(String code, String message) {
        super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public InternalServerErrorException(String code, String message, ErrorDTO data) {
        super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message, Arrays.asList(data));
    }
}
