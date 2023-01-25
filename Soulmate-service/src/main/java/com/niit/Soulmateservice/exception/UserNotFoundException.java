package com.niit.Soulmateservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "User does not Exists in database")
public class UserNotFoundException extends Exception {
    public UserNotFoundException(){
        super();
    }
}
