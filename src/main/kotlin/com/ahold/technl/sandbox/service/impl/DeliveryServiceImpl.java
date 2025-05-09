package com.ahold.technl.sandbox.service.impl;

import com.ahold.technl.sandbox.dto.BusinessSummary;
import com.ahold.technl.sandbox.dto.DeliveryIdRecord;
import com.ahold.technl.sandbox.dto.DeliveryInvoiceRecord;
import com.ahold.technl.sandbox.dto.DeliveryRecord;
import com.ahold.technl.sandbox.entity.Delivery;
import com.ahold.technl.sandbox.mapper.DeliveryMapper;
import com.ahold.technl.sandbox.repository.DeliveryRepo;
import com.ahold.technl.sandbox.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ahold.technl.sandbox.util.CollectionUtil.emptyIfNull;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepo deliveryRepo;

    private final DeliveryMapper deliveryMapper;

    @Override
    public DeliveryRecord createDelivery(DeliveryRecord deliveryRecord) {
        return null;
    }

    @Override
    public List<DeliveryInvoiceRecord> sendInvoice(DeliveryIdRecord deliveryIdRecord) {
        return null;
    }

    @Override
    public List<DeliveryRecord> findAll() {
        List<Delivery> allDelivery = deliveryRepo.findAll();
        return emptyIfNull(allDelivery).stream().map(deliveryMapper::toDTO).toList();
    }

    @Override
    public BusinessSummary getBusinessSummary() {
        return null;
    }
}
