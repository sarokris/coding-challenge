package com.ahold.technl.sandbox.dto

import com.ahold.technl.sandbox.validator.ValidDeliveryStatus
import jakarta.validation.constraints.NotNull


data class DeliveryRequest(
    val vehicleId: String,
    val address: String,
    @field:NotNull(message = "StartedAt cannot be null")
    val startedAt: String,
    val finishedAt: String? = null,
    @field:NotNull(message = "Status cannot be null")
    @field:ValidDeliveryStatus
    val status: DeliveryStatus
)

