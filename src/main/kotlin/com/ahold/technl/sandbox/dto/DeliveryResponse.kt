package com.ahold.technl.sandbox.dto

import java.time.OffsetDateTime
import java.util.*

data class DeliveryResponse(
    val id: String,
    val vehicleId: String,
    val address: String,
    val startedAt: String,
    val finishedAt: String?,
    val status: DeliveryStatus
)