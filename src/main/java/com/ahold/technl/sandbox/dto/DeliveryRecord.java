package com.ahold.technl.sandbox.dto;

import com.ahold.technl.sandbox.validation.ValidDeliveryStatus;
import com.ahold.technl.sandbox.validation.ValidDeliveryTime;
import jakarta.validation.constraints.NotNull;

@ValidDeliveryTime
public record DeliveryRecord(String id, String vehicleId, String address, @NotNull(message = "startedAt should not be null") String startedAt, String finishedAt
        , @ValidDeliveryStatus(enumClass = DeliveryStatus.class) DeliveryStatus status) {
}
