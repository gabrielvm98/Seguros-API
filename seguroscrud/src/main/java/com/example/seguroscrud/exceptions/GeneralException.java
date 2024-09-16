package com.example.seguroscrud.exceptions;

import com.example.seguroscrud.dtos.ErrorDTO;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GeneralException extends Exception{
    private final String code;
    private final int responseCode;
    private final List<ErrorDTO> errorList = new ArrayList<>();

    public GeneralException(String code, int responseCode, String message) {
        super(message);
        this.code = code;
        this.responseCode = responseCode;
    }

    public GeneralException(String code, int responseCode, String message, List<ErrorDTO> errorList) {
        super(message);
        this.code = code;
        this.responseCode = responseCode;
        this.errorList.addAll(errorList);
    }
}
