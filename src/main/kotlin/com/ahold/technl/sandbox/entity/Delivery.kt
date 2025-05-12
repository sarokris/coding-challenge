package com.ahold.technl.sandbox.entity

import com.ahold.technl.sandbox.dto.DeliveryStatus
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.time.OffsetDateTime
import java.util.*

@Entity
@Table
data class Delivery(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @field:NotNull
    val vehicleId: String,

    @field:NotNull
    val address: String,

    @field:NotNull
    val startedAt: OffsetDateTime,

    val finishedAt: OffsetDateTime? = null,

    @Enumerated(EnumType.STRING)
    val status: DeliveryStatus
)