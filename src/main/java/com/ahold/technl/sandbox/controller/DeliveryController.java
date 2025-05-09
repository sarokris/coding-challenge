package com.ahold.technl.sandbox.controller;

import com.ahold.technl.sandbox.dto.DeliveryInvoiceRecord;
import com.ahold.technl.sandbox.dto.DeliveryStatus;
import com.ahold.technl.sandbox.exception.DeliveryException;
import com.ahold.technl.sandbox.service.DeliveryService;
import com.ahold.technl.sandbox.dto.BusinessSummary;
import com.ahold.technl.sandbox.dto.DeliveryIdRecord;
import com.ahold.technl.sandbox.dto.DeliveryRecord;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
@Tag(name="AH Deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "This API  to create the deliveries started and finished"
    )
    public DeliveryRecord createDelivery(@Validated @RequestBody DeliveryRecord record){
        if (DeliveryStatus.IN_PROGRESS.equals(record.status()) && record.finishedAt() != null) {
            throw new DeliveryException(HttpStatus.BAD_REQUEST.value(), "finishedAt should be null for IN_PROGRESS deliveries");
        } else if (DeliveryStatus.DELIVERED.equals(record.status()) && record.finishedAt() == null) {
            throw new DeliveryException(HttpStatus.BAD_REQUEST.value(), "finishedAt should not be null for DELIVERED deliveries");
        }

        return deliveryService.createDelivery(record);
    }

    @PostMapping("/invoice")
    @Operation(
            summary = "This API is to send Invoice for the given deliveries"
    )
    public List<DeliveryInvoiceRecord> sendInvoice(@RequestBody DeliveryIdRecord deliveryIdRecords){
        return deliveryService.sendInvoice(deliveryIdRecords);
    }


    @GetMapping("/business-summary")
    @Operation(
            summary = "This API to get the delivery summary for yesterday"
    )
    public BusinessSummary getBusinessSummary() {
        return deliveryService.getBusinessSummary();
    }

}
