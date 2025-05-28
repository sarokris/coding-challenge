package com.ahold.technl.sandbox.util;

import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@UtilityClass
public class DateUtil {

    public static final ZoneId AMSTERDAM_ZONE_ID = ZoneId.of("Europe/Amsterdam");

    public static OffsetDateTime getStartOfToday(ZoneId zoneId){
        ZoneId givenZoneId = zoneId == null ? AMSTERDAM_ZONE_ID : zoneId;
        return OffsetDateTime.now(givenZoneId)
                .toLocalDate().atStartOfDay(givenZoneId).toOffsetDateTime();
    }

    public static OffsetDateTime getStartOfYesterday(ZoneId zoneId){
        return getStartOfToday(zoneId).minusDays(1);
    }

    public static OffsetDateTime getEndOfYesterday(ZoneId zoneId){
        return getStartOfToday(zoneId).minusNanos(1);
    }

    public static OffsetDateTime parse(String date){
        try {
            return OffsetDateTime.parse(date);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static String format(OffsetDateTime date, DateTimeFormatter formatter){
        if(date == null) return null;
        try {
            DateTimeFormatter dateTimeFormatter =  formatter == null ? DateTimeFormatter.ISO_OFFSET_DATE_TIME : formatter;
            return date.format(dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static String format(OffsetDateTime date){
        return format(date,null);
    }

}
