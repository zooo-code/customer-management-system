package com.example.cms.utils.common.infrastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class SystemClockHolderTest {

    @Test
    @DisplayName("time test")
    public void  timeTest() {
        SystemClockHolder systemClockHolder = new SystemClockHolder();
        long millis = systemClockHolder.millis();

        // 밀리초를 Instant으로 변환
        Instant instant = Instant.ofEpochMilli(millis);

        // Instant을 ZonedDateTime으로 변환
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, Clock.systemUTC().getZone());

        // 원하는 형식으로 출력 (예: ISO 형식)
        String formattedDateTime = zonedDateTime.format(DateTimeFormatter.ISO_DATE_TIME);

        // 결과 출력
        System.out.println("Millis: " + millis);
        System.out.println("DateTime: " + formattedDateTime);

        ZonedDateTime kstDateTime = instant.atZone(ZoneId.of("Asia/Seoul"));

        // 원하는 형식으로 출력 (예: ISO 형식)
        String korFormattedDateTime = kstDateTime.format(DateTimeFormatter.ISO_DATE_TIME);

        // 결과 출력
        System.out.println("UTC: " + instant);
        System.out.println("KST: " + korFormattedDateTime);
    }

}