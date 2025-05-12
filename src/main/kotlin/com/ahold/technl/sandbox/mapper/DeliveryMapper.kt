package com.ahold.technl.sandbox.mapper

import com.ahold.technl.sandbox.dto.DeliveryRequest
import com.ahold.technl.sandbox.entity.Delivery
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


@Mapper(componentModel = "spring")
interface DeliveryMapper {

    @Mapping(target = "startedAt", source = "startedAt", qualifiedByName = ["offsetDateTimeToString"])
    @Mapping(target = "finishedAt", source = "finishedAt", qualifiedByName = ["offsetDateTimeToString"])
    fun toDTO(delivery: Delivery?): DeliveryRequest?

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startedAt", source = "startedAt", qualifiedByName = ["stringToOffsetDateTime"])
    @Mapping(target = "finishedAt", source = "finishedAt", qualifiedByName = ["stringToOffsetDateTime"])
    fun toEntity(deliveryDTO: DeliveryRequest?): Delivery?

    @Named("stringToOffsetDateTime")
    fun stringToOffsetDateTime(dateTime: String?): OffsetDateTime? {
        return if (dateTime == null) null else OffsetDateTime.parse(dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }

    @Named("offsetDateTimeToString")
    fun offsetDateTimeToString(dateTime: OffsetDateTime?): String? {
        return dateTime?.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }
}