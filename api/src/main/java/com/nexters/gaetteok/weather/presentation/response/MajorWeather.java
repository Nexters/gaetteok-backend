package com.nexters.gaetteok.weather.presentation.response;

import com.nexters.gaetteok.weather.enums.Weather;
import lombok.Getter;

@Getter
public enum MajorWeather {

    CLEAR("나갈 때 물통을 챙겨주세요 💦"),
    RAIN("미끄러지지 않게 살펴주세요 🐾"),
    SNOW("같이 눈사람을 만들어볼까요? ⛄️"),
    CLOUDS("비 오기 전에 얼른 산책 다녀와요!"),
    DUST("짧고 빠르게 산책시켜주세요 😷"),
    ;

    private final String message;

    MajorWeather(String message) {
        this.message = message;
    }

    public static MajorWeather from(Weather weather) {
        return switch (weather) {
            case RAIN, THUNDERSTORM, DRIZZLE, SQUALL -> RAIN;
            case SNOW -> SNOW;
            case MIST, HAZE, DUST, FOG, SMOKE, ASH -> DUST;
            case CLOUDS, SAND, TORNADO -> CLOUDS;
            case CLEAR -> CLEAR;
        };
    }

}
