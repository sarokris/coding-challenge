package com.ahold.technl.sandbox.mapper;

import com.ahold.technl.sandbox.dto.DeliveryRecord;
import com.ahold.technl.sandbox.entity.Delivery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    DeliveryRecord toDTO(Delivery delivery);
    Delivery toEntity(DeliveryRecord deliveryDTO);
}
