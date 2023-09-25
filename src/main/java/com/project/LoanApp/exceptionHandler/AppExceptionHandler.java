package com.project.LoanApp.exceptionHandler;

import com.project.LoanApp.customException.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomBadRequestException(CustomException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
