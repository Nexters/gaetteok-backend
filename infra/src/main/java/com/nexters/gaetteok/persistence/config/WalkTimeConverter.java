package com.nexters.gaetteok.persistence.config;

import com.nexters.gaetteok.domain.WalkTime;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class WalkTimeConverter implements AttributeConverter<WalkTime, String> {

    @Override
    public String convertToDatabaseColumn(WalkTime attribute) {
        return attribute.getDescription();
    }

    @Override
    public WalkTime convertToEntityAttribute(String dbData) {
        return WalkTime.findByDescription(dbData);
    }

}
