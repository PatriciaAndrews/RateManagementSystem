package com.updatedrms.rate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleGenericNotFoundException(NotFoundException e) {
        CustomErrorResponse error = new CustomErrorResponse("NOT_FOUND", e.getMessage());
        error.setTimestamp(LocalDateTime.now());
        error.setStatus((HttpStatus.NOT_FOUND.value()));
        error.setErrorMsg("RateId not found in RMS");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InternalServerException.class)
    public ResponseEntity<CustomErrorResponse> handleInternalServerException(InternalServerException e) {
        CustomErrorResponse error = new CustomErrorResponse("INTERNAL_SERVER_ERROR", e.getMessage());
        error.setTimestamp(LocalDateTime.now());
        error.setStatus((HttpStatus.INTERNAL_SERVER_ERROR.value()));
        error.setErrorMsg("Internal server error. Please contact admin");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = NoDataFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleNoDataFoundException(NoDataFoundException e) {
        CustomErrorResponse error = new CustomErrorResponse("NO_DATA", e.getMessage());
        error.setTimestamp(LocalDateTime.now());
        error.setStatus((HttpStatus.NOT_FOUND.value()));
        error.setErrorMsg("No Rates Found in  RMS");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
