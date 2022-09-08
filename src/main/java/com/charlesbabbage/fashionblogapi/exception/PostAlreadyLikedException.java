package com.charlesbabbage.fashionblogapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostAlreadyLikedException extends RuntimeException{
    private String message;
}
