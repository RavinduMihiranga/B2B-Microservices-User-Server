package com.B2B.EcommerceApp.UserService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class Unauthorized extends RuntimeException{
    public Unauthorized(String message) {
        super(message);
    }

}
