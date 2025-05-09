package com.ahold.technl.sandbox.mapper;

import com.ahold.technl.sandbox.dto.DeliveryRecord;
import com.ahold.technl.sandbox.entity.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    DeliveryRecord toDTO(Delivery delivery);
    @Mapping(target = "id", ignore = true)
    Delivery toEntity(DeliveryRecord deliveryDTO);
}
