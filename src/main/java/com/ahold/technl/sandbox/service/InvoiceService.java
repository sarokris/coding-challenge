package com.ahold.technl.sandbox.service;

import com.ahold.technl.sandbox.dto.DeliveryInvoiceRecord;
import com.ahold.technl.sandbox.entity.Delivery;

public interface InvoiceService {
    DeliveryInvoiceRecord invokeInvoiceApi(Delivery delivery);
}
