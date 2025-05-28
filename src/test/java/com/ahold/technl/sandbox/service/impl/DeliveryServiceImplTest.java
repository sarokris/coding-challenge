package com.ahold.technl.sandbox.service.impl;

import com.ahold.technl.sandbox.dto.DeliveryRecord;
import com.ahold.technl.sandbox.dto.DeliveryStatus;
import com.ahold.technl.sandbox.entity.Delivery;
import com.ahold.technl.sandbox.mapper.DeliveryMapper;
import com.ahold.technl.sandbox.repository.DeliveryRepo;
import com.ahold.technl.sandbox.service.InvoiceService;
import com.ahold.technl.sandbox.service.impl.DeliveryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import static com.ahold.technl.sandbox.util.DateUtil.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceImplTest {

    private static final String ADDRESS = "Den Haag";
    private static final String VEHICLE_ID = "101-KLM";

    private static final String ID = "101";
    private static final String CURRENT_TIME = OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);


    @Mock
    private DeliveryRepo deliveryRepo;
    @Spy
    private DeliveryMapper deliveryMapper = Mappers.getMapper(DeliveryMapper.class);;

    @Mock
    private InvoiceService invoiceService;

    @InjectMocks
    DeliveryServiceImpl deliveryService;

    @Test
    void testFindAll_NoDelivery(){
        when(deliveryRepo.findAll()).thenReturn(List.of());
        List<DeliveryRecord> all = deliveryService.findAll();
        assertTrue(CollectionUtils.isEmpty(all));
    }

    @ParameterizedTest
    @MethodSource("getDelivery")
    void testFindAll_SomeDeliveryResults(Delivery delivery){
        DeliveryRecord expectedResp = deliveryMapper.toDTO(delivery);
        when(deliveryRepo.findAll()).thenReturn(List.of(delivery));
        List<DeliveryRecord> all = deliveryService.findAll();
        assertFalse(CollectionUtils.isEmpty(all));
        assertTrue(List.of(expectedResp).containsAll(all));
    }


    @ParameterizedTest
    @MethodSource("getDeliveryAndRecord")
    void testCreateDelivery(DeliveryRecord record,Delivery delivery){
        when(deliveryRepo.save(any(Delivery.class))).thenReturn(delivery);
        DeliveryRecord deliveryRecord = deliveryService.createDelivery(record);
        assertNotNull(deliveryRecord);
        assertNotNull(deliveryRecord.id());
        assertEquals(delivery.getId(),deliveryRecord.id());
        assertEquals(delivery.getAddress(),deliveryRecord.address());
        assertEquals(format(delivery.getStartedAt()),deliveryRecord.startedAt());
        assertEquals(format(delivery.getFinishedAt()),deliveryRecord.finishedAt());
        assertEquals(delivery.getStatus(),deliveryRecord.status().name());


    }


    private static Stream<Arguments> getDelivery(){
        Delivery delivery = getDeliveryEntity();
        return Stream.of(Arguments.of(delivery));
    }

    private static Stream<Arguments> getDeliveryAndRecord(){
        Delivery delivery = getDeliveryEntity();
        DeliveryRecord record = getDeliveryRecord();
        return Stream.of(Arguments.of(record,delivery));
    }

    private static DeliveryRecord getDeliveryRecord() {
        return new DeliveryRecord(null,VEHICLE_ID,ADDRESS,CURRENT_TIME,CURRENT_TIME,DeliveryStatus.DELIVERED);
    }

    private static Delivery getDeliveryEntity() {
        Delivery delivery = new Delivery();
        delivery.setId("101");
        delivery.setStartedAt(OffsetDateTime.now());
        delivery.setFinishedAt(OffsetDateTime.now());
        delivery.setVehicleId(VEHICLE_ID);
        delivery.setAddress(ADDRESS);
        delivery.setStatus(DeliveryStatus.DELIVERED.name());
        return delivery;
    }


}