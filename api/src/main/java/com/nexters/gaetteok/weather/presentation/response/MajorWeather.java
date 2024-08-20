package com.nexters.gaetteok.weather.presentation.response;

import com.nexters.gaetteok.weather.enums.Weather;
import lombok.Getter;

@Getter
public enum MajorWeather {

    CLEAR("맑은 날씨입니다."),
    RAIN("우산을 챙겨주세요."),
    SNOW("눈이 오는 날씨입니다."),
    CLOUDS("구름이 많은 날씨입니다."),
    ;

    private final String message;

    MajorWeather(String message) {
        this.message = message;
    }

    public static MajorWeather from(Weather weather) {
        return switch (weather) {
            case RAIN, THUNDERSTORM, DRIZZLE -> RAIN;
            case SNOW -> SNOW;
            case CLOUDS, MIST, HAZE, DUST, FOG, SAND, SQUALL, TORNADO, SMOKE, ASH -> CLOUDS;
            case CLEAR -> CLEAR;
        };
    }

}
