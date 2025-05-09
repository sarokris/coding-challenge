package com.ahold.technl.sandbox.dto;

import com.ahold.technl.sandbox.validation.ValidDeliveryStatus;

public record DeliveryRecord(String id, String vehicleId, String address, String startedAt, String finishedAt
        , @ValidDeliveryStatus(enumClass = DeliveryStatus.class, message = "Invalid status") DeliveryStatus status) {
}
