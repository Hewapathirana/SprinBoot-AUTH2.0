package com.codetech.oauth2.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Created by A Dasitha.
 * Date: 7/8/2019
 * Time: 10:48 AM
 */


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = InvalidLoginResponse.class)
    public ResponseEntity<Object> invalidLoginResponse() {
        return new ResponseEntity<>("User name not found...", HttpStatus.NOT_FOUND);
    }


}
