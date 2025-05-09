package com.ahold.technl.sandbox.controller;

import com.ahold.technl.sandbox.dto.DeliveryInvoiceRecord;
import com.ahold.technl.sandbox.dto.DeliveryStatus;
import com.ahold.technl.sandbox.exception.DeliveryException;
import com.ahold.technl.sandbox.service.DeliveryService;
import com.ahold.technl.sandbox.dto.BusinessSummary;
import com.ahold.technl.sandbox.dto.DeliveryIdRecord;
import com.ahold.technl.sandbox.dto.DeliveryRecord;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryRecord createDelivery(@Valid @RequestBody DeliveryRecord record){
        if(DeliveryStatus.IN_PROGRESS.equals(record.status()) && record.finishedAt() != null){
            throw new DeliveryException(HttpStatus.BAD_REQUEST.value(), "finishedAt should be null for IN_PROGRESS deliveries");
        }
       return deliveryService.createDelivery(record);
    }

    @PostMapping("/invoice")
    public List<DeliveryInvoiceRecord> sendInvoice(@RequestBody List<DeliveryIdRecord> deliveryIdRecords){
        return null;
    }


    @GetMapping("/business-summary")
    public BusinessSummary getBusinessSummary() {
        return deliveryService.getBusinessSummary();
    }

}
