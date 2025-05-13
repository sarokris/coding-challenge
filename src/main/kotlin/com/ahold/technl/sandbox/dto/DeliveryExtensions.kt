package com.ahold.technl.sandbox.dto

import com.ahold.technl.sandbox.entity.Delivery
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

fun OffsetDateTime?.toFormattedString(): String? = this?.format(formatter)

fun String.toOffsetDateTimeOrNull(): OffsetDateTime? = try {
    OffsetDateTime.parse(this, formatter)
} catch (e: Exception) {
    null
}
fun DeliveryRequest.toEntity(): Delivery {
    return Delivery(
        vehicleId = this.vehicleId,
        address = this.address,
        startedAt = this.startedAt.toOffsetDateTimeOrNull() ?: throw IllegalArgumentException("Invalid startedAt format"),
        finishedAt = this.finishedAt?.toOffsetDateTimeOrNull(),
        status = this.status
    )
}

fun Delivery.toResponse(): DeliveryResponse {
    return DeliveryResponse(
        id = this.id.toString(),
        vehicleId = this.vehicleId,
        address = this.address,
        startedAt = this.startedAt.toFormattedString() ?: "",
        finishedAt = this.finishedAt.toFormattedString(),
        status = this.status
    )
}

fun String.toUUID(): UUID {
    return try {
        UUID.fromString(this)
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("Invalid UUID string: $this")
    }
}

fun List<String>.toUUIDList(): List<UUID> {
    return this.map { it.toUUID() }
}