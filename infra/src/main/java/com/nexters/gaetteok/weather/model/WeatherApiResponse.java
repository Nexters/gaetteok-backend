package com.nexters.gaetteok.weather.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WeatherApiResponse {

    private List<WeatherField> weather;
    private MainField main;
}
