package com.ahold.technl.sandbox.dto;

import com.ahold.technl.sandbox.validation.ValidDeliveryStatus;
import jakarta.validation.constraints.NotNull;

public record DeliveryRecord(String id, String vehicleId, String address, @NotNull(message = "startedAt should not be null") String startedAt, String finishedAt
        , DeliveryStatus status) {
}
