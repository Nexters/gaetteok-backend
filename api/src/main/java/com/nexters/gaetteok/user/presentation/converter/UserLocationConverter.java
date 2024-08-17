package com.nexters.gaetteok.user.presentation.converter;

import com.nexters.gaetteok.weather.enums.City;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserLocationConverter implements Converter<String, City> {

    @Override
    public City convert(String source) {
        return City.valueOf(source);
    }
}
