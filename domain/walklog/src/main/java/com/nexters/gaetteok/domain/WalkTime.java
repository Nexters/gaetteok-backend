package com.nexters.gaetteok.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Nonnull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum WalkTime {

    NONE("없음"),  // 없음
    WITHIN_20_MINUTES("20분 내외"),  // 20분 내외
    BETWEEN_20_AND_40_MINUTES("20분~40분"),  // 20분~40분
    BETWEEN_40_MINUTES_AND_1_HOUR("40분~1시간"),  // 40분~1시간
    ;

    private final String description;
    private static final Map<String, WalkTime> descriptionMap = Arrays.stream(values())
        .collect(Collectors.toMap(WalkTime::getDescription, e -> e));

    WalkTime(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    public static WalkTime findByDescription(String description) {
        return descriptionMap.get(description);
    }

    @Component
    public static class WalkTimeConverter implements Converter<String, WalkTime> {
        @Override
        public WalkTime convert(@Nonnull String source) {
            return WalkTime.findByDescription(source);
        }
    }

}
