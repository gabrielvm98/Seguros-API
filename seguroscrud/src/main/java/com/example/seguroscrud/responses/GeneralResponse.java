package com.example.seguroscrud.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse<T> {
    private String status;
    private String code;
    private String message;
    private T data;

    public GeneralResponse(String status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
