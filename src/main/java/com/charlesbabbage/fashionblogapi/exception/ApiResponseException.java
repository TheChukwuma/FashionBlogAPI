package com.charlesbabbage.fashionblogapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ApiResponseException extends RuntimeException{
    private HttpStatus status;
    private String details;
    private boolean done;

    public ApiResponseException(HttpStatus status, String details) {
        this.status = status;
        this.details = details;
    }
}
