package com.ahold.technl.sandbox.dto;

import com.ahold.technl.sandbox.validation.ValidDeliveryStatus;
import jakarta.validation.constraints.NotNull;

public record DeliveryRecord(String id, String vehicleId, String address, @NotNull String startedAt, String finishedAt
        , @ValidDeliveryStatus(enumClass = DeliveryStatus.class, message = "Invalid status") DeliveryStatus status) {
}
