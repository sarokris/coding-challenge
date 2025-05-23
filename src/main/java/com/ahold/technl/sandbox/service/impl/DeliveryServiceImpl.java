package com.ahold.technl.sandbox.service.impl;

import com.ahold.technl.sandbox.dto.BusinessSummary;
import com.ahold.technl.sandbox.dto.DeliveryIdRecord;
import com.ahold.technl.sandbox.dto.DeliveryInvoiceRecord;
import com.ahold.technl.sandbox.dto.DeliveryRecord;
import com.ahold.technl.sandbox.entity.Delivery;
import com.ahold.technl.sandbox.exception.DeliveryException;
import com.ahold.technl.sandbox.mapper.DeliveryMapper;
import com.ahold.technl.sandbox.repository.DeliveryRepo;
import com.ahold.technl.sandbox.service.DeliveryService;
import com.ahold.technl.sandbox.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.ahold.technl.sandbox.util.CollectionUtil.emptyIfNull;
import static com.ahold.technl.sandbox.util.DateUtil.AMSTERDAM_ZONE_ID;
import static com.ahold.technl.sandbox.util.DateUtil.getEndOfYesterday;
import static com.ahold.technl.sandbox.util.DateUtil.getStartOfYesterday;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepo deliveryRepo;

    private final DeliveryMapper deliveryMapper;

    private final InvoiceService invoiceService;

    @Override
    public DeliveryRecord createDelivery(DeliveryRecord deliveryRecord) {
        Delivery delivery = deliveryRepo.save(deliveryMapper.toEntity(deliveryRecord));
        return deliveryMapper.toDTO(delivery);
    }

    @Override
    public List<DeliveryInvoiceRecord> sendInvoice(DeliveryIdRecord deliveryIdRecord) {
        List<Delivery> deliveries = deliveryRepo.findAllById(deliveryIdRecord.deliveryIds());

        List<String> missingIds = deliveryIdRecord.deliveryIds().stream()
                .filter(id -> deliveries.stream().noneMatch(delivery -> delivery.getId().equals(id)))
                .toList();

        if (!missingIds.isEmpty()) {
            throw new DeliveryException(HttpStatus.NOT_FOUND.value(), "The following delivery IDs were not found: " + missingIds);
        }
        return deliveries.parallelStream().map(invoiceService::invokeInvoiceApi).toList();
    }

    @Override
    public List<DeliveryRecord> findAll() {
        List<Delivery> allDelivery = deliveryRepo.findAll();
        return emptyIfNull(allDelivery).stream().map(deliveryMapper::toDTO).toList();
    }

    @Override
    public BusinessSummary getBusinessSummary() {

        List<Delivery> yesterdaysDeliveries = deliveryRepo.findAllByStartedAtBetween(getStartOfYesterday(AMSTERDAM_ZONE_ID),getEndOfYesterday(AMSTERDAM_ZONE_ID));

        if(CollectionUtils.isEmpty(yesterdaysDeliveries))
            throw new DeliveryException(HttpStatus.NOT_FOUND.value(), "No delivery on yesterday");

        List<Long> timeDifferences = new ArrayList<>();
        yesterdaysDeliveries.sort(Comparator.comparing(Delivery::getStartedAt));

        for (int i = 1; i < yesterdaysDeliveries.size(); i++) {
            OffsetDateTime previousStartTime = yesterdaysDeliveries.get(i - 1).getStartedAt();
            OffsetDateTime currentStartTime = yesterdaysDeliveries.get(i).getStartedAt();

            long difference = ChronoUnit.MINUTES.between(previousStartTime, currentStartTime);
            timeDifferences.add(difference);
        }

        // Calculate average time difference
        double averageTimeDifference = timeDifferences.stream()
                .mapToLong(Long::longValue)
                .average()
                .orElse(0);

        return new BusinessSummary(yesterdaysDeliveries.size(), (long) averageTimeDifference);

    }

}
