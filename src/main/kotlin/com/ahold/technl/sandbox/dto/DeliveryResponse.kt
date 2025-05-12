package com.ahold.technl.sandbox.dto

import java.time.OffsetDateTime
import java.util.*

data class DeliveryResponse(
    val id: UUID,
    val vehicleId: String,
    val address: String,
    val startedAt: OffsetDateTime,
    val finishedAt: OffsetDateTime?,
    val status: DeliveryStatus
)