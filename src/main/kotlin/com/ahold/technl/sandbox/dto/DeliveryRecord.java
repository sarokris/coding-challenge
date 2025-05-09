package com.ahold.technl.sandbox.dto;

public record DeliveryRecord(String id,String vehicleId,String address,String startedAt,String finishedAt,DeliveryStatus status) {
}
