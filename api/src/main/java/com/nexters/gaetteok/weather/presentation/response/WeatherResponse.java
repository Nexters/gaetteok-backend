package com.nexters.gaetteok.weather.presentation.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WeatherResponse {

    private Integer temp;
    private Weather weather;

    @Builder
    public WeatherResponse(Integer temp, Weather weather) {
        this.temp = temp;
        this.weather = weather;
    }
}
