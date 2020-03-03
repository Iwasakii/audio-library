package com.ipiecoles.java.audio.exception;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEntityNotFoundException(IllegalArgumentException illegalArgumentException){
        return illegalArgumentException.getMessage();
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected String handleConflictException(ConflictException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected String handleConversionFailedException(ConversionFailedException ex) {
        return "La valeur " + ex.getValue() + " est incorrecte.";
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected String handleConversionFailedException(HttpRequestMethodNotSupportedException ex) {
        return "La commande " + ex.getMethod() + " n'est pas autorisée.";

    }


}
