package com.ahold.technl.sandbox.service;

import com.ahold.technl.sandbox.dto.BusinessSummary;
import com.ahold.technl.sandbox.dto.DeliveryIdRecord;
import com.ahold.technl.sandbox.dto.DeliveryInvoiceRecord;
import com.ahold.technl.sandbox.dto.DeliveryRecord;

import java.util.List;

public interface DeliveryService {

    DeliveryRecord createDelivery(DeliveryRecord deliveryRecord);

    List<DeliveryInvoiceRecord> sendInvoice(DeliveryIdRecord deliveryIdRecord);

    List<DeliveryRecord> findAll();

    BusinessSummary getBusinessSummary();
}
