package com.ahold.technl.sandbox.service

import com.ahold.technl.sandbox.dto.DeliveryRequest
import com.ahold.technl.sandbox.dto.DeliveryResponse

interface DeliveryService {
    fun createDelivery(request: DeliveryRequest): DeliveryResponse
}