package com.ecommerce.oms.controller.handler;

import com.ecommerce.oms.domain.api.ApiErrorResponse;
import com.ecommerce.oms.exception.ParseException;
import com.ecommerce.oms.exception.ResourceNotFoundException;
import com.ecommerce.oms.exception.ValidationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> exceptionHandler(ResourceNotFoundException e, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ApiErrorResponse> exceptionHandler(ValidationException e, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ParseException.class})
    public ResponseEntity<ApiErrorResponse> exceptionHandler(ParseException e, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
