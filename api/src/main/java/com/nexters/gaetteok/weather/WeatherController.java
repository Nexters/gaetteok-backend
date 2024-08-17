package com.nexters.gaetteok.weather;

import com.nexters.gaetteok.weather.presentation.response.Weather;
import com.nexters.gaetteok.weather.presentation.response.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController implements WeatherSpecification {

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WeatherResponse> getWeather(
        @RequestParam String city
    ) {

        return ResponseEntity.ok(WeatherResponse.builder()
            .weather(Weather.SUN)
            .temp(10)
            .build());
    }

}
