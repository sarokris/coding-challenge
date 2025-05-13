package com.ahold.technl.sandbox.dto;

import com.ahold.technl.sandbox.util.DeliveryStatusDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = DeliveryStatusDeserializer.class)
public enum DeliveryStatus {
    IN_PROGRESS, DELIVERED
}
