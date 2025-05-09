package com.ahold.technl.sandbox.mapper;

import com.ahold.technl.sandbox.dto.DeliveryRecord;
import com.ahold.technl.sandbox.entity.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @Mapping(target = "startedAt", source = "startedAt", qualifiedByName = "offsetDateTimeToString")
    @Mapping(target = "finishedAt", source = "finishedAt", qualifiedByName = "offsetDateTimeToString")
    DeliveryRecord toDTO(Delivery delivery);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startedAt", source = "startedAt", qualifiedByName = "stringToOffsetDateTime")
    @Mapping(target = "finishedAt", source = "finishedAt", qualifiedByName = "stringToOffsetDateTime")
    Delivery toEntity(DeliveryRecord deliveryDTO);

    @Named("stringToOffsetDateTime")
    default OffsetDateTime stringToOffsetDateTime(String dateTime) {
        return dateTime == null ? null : OffsetDateTime.parse(dateTime, FORMATTER);
    }

    @Named("offsetDateTimeToString")
    default String offsetDateTimeToString(OffsetDateTime dateTime) {
        return dateTime == null ? null : dateTime.format(FORMATTER);
    }
}
