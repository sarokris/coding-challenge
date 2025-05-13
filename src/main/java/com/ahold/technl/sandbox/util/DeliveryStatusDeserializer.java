package com.ahold.technl.sandbox.util;

import com.ahold.technl.sandbox.dto.DeliveryStatus;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Arrays;

public class DeliveryStatusDeserializer extends JsonDeserializer<DeliveryStatus> {

    @Override
    public DeliveryStatus deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        String status = jsonParser.getText().toUpperCase();
        for (DeliveryStatus deliveryStatus : DeliveryStatus.values()) {
            if (deliveryStatus.name().equals(status)) {
                return deliveryStatus;
            }
        }
        throw new IllegalArgumentException("Invalid DeliveryStatus: " + status +"and allowed values are "+ Arrays.toString(DeliveryStatus.values()));
    }
}
