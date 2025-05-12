package com.ahold.technl.sandbox.dto

data class DeliveryRequest(
    val vehicleId: String,
    val address: String,
    val startedAt: String,
    val finishedAt: String? = null,
    val status: DeliveryStatus
)

