package com.nexters.gaetteok.weather.enums;

import lombok.Getter;

@Getter
public enum City {
    SEOUL("37.568", "126.978"),
    BUSAN("35.103", "129.040"),
    DAEGU("35.800", "128.550"),
    INCHEON("37.450", "126.416"),
    GWANGJU("35.155", "126.916"),
    DAEJEON("36.333", "127.417"),
    ULSAN("35.537", "129.317"),
    SEJONG("36.482", "127.287"),
    GYEONGGI("37.439", "127.138"),
    GANGWON("37.351", "127.945"),
    CHUNGBUK("36.971", "127.932"),
    CHUNGNAM("36.601", "126.665"),
    JEONBUK("35.822", "127.149"),
    JEONNAME("34.948", "127.490"),
    GYEONGBUK("36.566", "128.725"),
    GYEONGNAM("35.228", "128.681"),
    JEJU("33.510", "126.522");

    private final String lat;
    private final String lon;

    City(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

}
