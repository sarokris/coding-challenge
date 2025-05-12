package com.ahold.technl.sandbox.controller

import com.ahold.technl.sandbox.dto.DeliveryRequest
import com.ahold.technl.sandbox.dto.DeliveryResponse
import com.ahold.technl.sandbox.dto.DeliveryStatus
import com.ahold.technl.sandbox.exception.DeliveryException
import com.ahold.technl.sandbox.service.DeliveryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/deliveries")
class DeliveryController(private val deliveryService: DeliveryService) {

    @PostMapping
    fun createDelivery(@Valid @RequestBody request: DeliveryRequest): DeliveryResponse {
        if (DeliveryStatus.IN_PROGRESS == request.status && request.finishedAt != null) {
            throw DeliveryException(
                HttpStatus.BAD_REQUEST.value(),
                "finishedAt should be null for IN_PROGRESS deliveries"
            )
        } else if (DeliveryStatus.DELIVERED == request.status && request.finishedAt == null) {
            throw DeliveryException(
                HttpStatus.BAD_REQUEST.value(),
                "finishedAt should not be null for DELIVERED deliveries"
            )
        }
        var response = deliveryService.createDelivery(request)
        return response
    }
}