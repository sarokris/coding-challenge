package com.ahold.technl.sandbox.exception;

import com.ahold.technl.sandbox.dto.DeliveryErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DeliveryControllerAdvice {

    @ExceptionHandler(DeliveryException.class)
    public ResponseEntity<DeliveryErrorResponse> handleHolidayExceptioException(DeliveryException ex) {
        DeliveryErrorResponse error = new DeliveryErrorResponse(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(error, resolveHttpStatus(error.errCode()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid value provided: " + ex.getMessage());
    }

    private HttpStatus resolveHttpStatus(int errCode){
        if(errCode == 0)
            return HttpStatus.INTERNAL_SERVER_ERROR;
        HttpStatus status =  HttpStatus.resolve(errCode);
        return status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
