package com.ahold.technl.sandbox.util;

import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;
import java.time.ZoneId;

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

}
