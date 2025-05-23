package com.ahold.technl.sandbox.exception;

import com.ahold.technl.sandbox.dto.DeliveryErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DeliveryControllerAdvice {

    @ExceptionHandler(DeliveryException.class)
    public ResponseEntity<DeliveryErrorResponse> handleHolidayExceptioException(DeliveryException ex) {
        DeliveryErrorResponse error = new DeliveryErrorResponse(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(error, resolveHttpStatus(error.errCode()));
    }

    @ExceptionHandler(DeliveryNotFoundException.class)
    public ResponseEntity<DeliveryErrorResponse> handleHolidayExceptioException(DeliveryNotFoundException ex) {
        DeliveryErrorResponse error = new DeliveryErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, resolveHttpStatus(error.errCode()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid value provided: " + ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleConstraintViolationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        String errorMessage = errors.values().stream().findFirst().orElse("Validation error");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleEnumErrors(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) ex.getCause();
            if (ife.getTargetType().isEnum()) {
                return ResponseEntity
                        .badRequest()
                        .body("Invalid value for Delivery Status. Accepted values are: " +
                                Arrays.toString(ife.getTargetType().getEnumConstants()));
            }
        }
        return ResponseEntity.badRequest().body("Invalid request payload");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleInvalidEnumException(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    private HttpStatus resolveHttpStatus(int errCode){
        if(errCode == 0)
            return HttpStatus.INTERNAL_SERVER_ERROR;
        HttpStatus status =  HttpStatus.resolve(errCode);
        return status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
