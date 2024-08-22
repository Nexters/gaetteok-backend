package com.nexters.gaetteok.persistence.config;

import com.nexters.gaetteok.domain.WalkTime;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class WalkTimeConverter implements AttributeConverter<WalkTime, String> {

    @Override
    public String convertToDatabaseColumn(WalkTime attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getDescription();
    }

    @Override
    public WalkTime convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return WalkTime.findByDescription(dbData);
    }

}
