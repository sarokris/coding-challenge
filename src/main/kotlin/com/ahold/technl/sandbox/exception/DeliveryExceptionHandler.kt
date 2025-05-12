package com.ahold.technl.sandbox.exception

import com.ahold.technl.sandbox.dto.DeliveryErrorResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class DeliveryExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val errors = mutableMapOf<String, String>()
        
        for (error in ex.bindingResult.allErrors) {
            if (error is FieldError) {
                errors[error.field] = error.defaultMessage ?: "Validation error"
            }
        }
        
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(DeliveryException::class)
    fun handleHolidayExceptioException(ex: DeliveryException): ResponseEntity<DeliveryErrorResponse> {
        val error: DeliveryErrorResponse = DeliveryErrorResponse(ex.errorCode, ex.errorMsg)
        return ResponseEntity<DeliveryErrorResponse>(error, resolveHttpStatus(error.errCode))
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid value provided: " + ex.message)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleJsonParseException(ex: HttpMessageNotReadableException): ResponseEntity<Map<String, String>> {
        val errorMessage = ex.cause?.message ?: "Invalid request payload."
        return ResponseEntity(mapOf("error" to errorMessage), HttpStatus.BAD_REQUEST)
    }

    private fun resolveHttpStatus(errCode: Int): HttpStatus {
        if (errCode == 0) return HttpStatus.INTERNAL_SERVER_ERROR
        val status = HttpStatus.resolve(errCode)
        return status ?: HttpStatus.INTERNAL_SERVER_ERROR
    }
}
