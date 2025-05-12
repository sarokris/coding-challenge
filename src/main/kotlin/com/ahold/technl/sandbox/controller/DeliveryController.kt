package com.ahold.technl.sandbox.controller

import com.ahold.technl.sandbox.dto.DeliveryRequest
import com.ahold.technl.sandbox.dto.DeliveryResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/deliveries")
class DeliveryController {

    @PostMapping
    fun createDelivery(@RequestBody request: DeliveryRequest): String {
        return "Success"
    }
}