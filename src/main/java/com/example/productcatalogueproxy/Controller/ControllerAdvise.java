package com.example.productcatalogueproxy.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<String> handleException(){
        return new ResponseEntity<String>("Illegal Argument", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
