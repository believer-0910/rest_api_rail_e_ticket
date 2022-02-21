package com.example.rail_e_ticket_api.config;

import com.example.rail_e_ticket_api.exception.ApiExceptionResponse;
import com.example.rail_e_ticket_api.exception.CustomException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionResponse apiExceptionResponse
                = new ApiExceptionResponse();
        apiExceptionResponse.setError(ex.getMessage());
        apiExceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.ok(apiExceptionResponse);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException customException){
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse();
        apiExceptionResponse.setError(customException.getMessage());
        apiExceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.ok(apiExceptionResponse);
    }
}
