package com.ahold.technl.sandbox.service

import com.ahold.technl.sandbox.dto.*


interface DeliveryService {
    fun createDelivery(request: DeliveryRequest): DeliveryResponse

    fun sendInvoice(deliveryIdRecord: DeliveryIdRecord): MutableList<DeliveryInvoiceRecord?>?

    fun getBusinessSummary(): BusinessSummary
}