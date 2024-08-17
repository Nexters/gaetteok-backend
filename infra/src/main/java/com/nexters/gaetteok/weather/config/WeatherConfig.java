package com.nexters.gaetteok.weather.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "weather")
@AllArgsConstructor
public class WeatherConfig {

    private String apiKey;
    private String apiPath;
}
