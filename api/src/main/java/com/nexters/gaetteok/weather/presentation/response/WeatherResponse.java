package com.nexters.gaetteok.weather.presentation.response;

import com.nexters.gaetteok.weather.enums.Weather;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class WeatherResponse {

    @Schema(description = "온도")
    private Integer temp;

    @Schema(description = "날씨")
    private Weather weather;

    @Builder
    public WeatherResponse(Integer temp, Weather weather) {
        this.temp = temp;
        this.weather = weather;
    }
}
