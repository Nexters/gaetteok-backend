package com.nexters.gaetteok.weather.enums;

import lombok.Getter;

@Getter
public enum Weather {
    THUNDERSTORM("우산을 챙겨주세요"),
    DRIZZLE("우산을 챙겨주세요"),
    RAIN("우산을 챙겨주세요"),

    SNOW("눈이 내립니다"),

    SMOKE("연기가 많이 납니다"),
    ASH("화산재가 날립니다"),

    MIST("안개가 낀 날씨입니다"),
    HAZE("안개가 낀 날씨입니다"),
    DUST("미세먼지가 많습니다"),
    FOG("안개가 낀 날씨입니다"),
    SAND("모래가 많이 날립니다"),
    SQUALL("돌풍이 불고 있습니다"),
    TORNADO("토네이도가 발생했습니다"),
    CLOUDS("구름이 많이 끼어 있습니다"),

    CLEAR("맑은 날씨입니다");

    private final String message;

    Weather(String message) {
        this.message = message;
    }

}
