package com.nexters.gaetteok.weather.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class WeatherApiResponse {

    private List<WeatherField> weather;
    private MainField main;
}
