package com.ahold.technl.sandbox.service

import com.ahold.technl.sandbox.dto.DeliveryRequest
import com.ahold.technl.sandbox.dto.DeliveryResponse
import com.ahold.technl.sandbox.dto.toEntity
import com.ahold.technl.sandbox.dto.toResponse
import com.ahold.technl.sandbox.repo.DeliveryRepository
import org.springframework.stereotype.Service

@Service
class DeliveryServiceImpl(
    private val deliveryRepository: DeliveryRepository
) : DeliveryService {

    override fun createDelivery(request: DeliveryRequest): DeliveryResponse {
        val delivery = request.toEntity()
        val savedDelivery =  deliveryRepository.save(delivery)
        return savedDelivery.toResponse()
    }
}