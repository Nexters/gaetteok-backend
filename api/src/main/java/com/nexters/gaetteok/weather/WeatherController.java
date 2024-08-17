package com.nexters.gaetteok.weather;

import com.nexters.gaetteok.weather.enums.City;
import com.nexters.gaetteok.weather.enums.Weather;
import com.nexters.gaetteok.weather.model.WeatherApiResponse;
import com.nexters.gaetteok.weather.presentation.response.WeatherResponse;
import com.nexters.gaetteok.weather.service.WeatherService;
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

    private final Double KELVIN_TEMP = 273.15;

    private final WeatherService weatherService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WeatherResponse> getWeather(
        @RequestParam City city
    ) {
        WeatherApiResponse weatherApiResponse = weatherService.getWeather(city);
        Weather weather = Weather.valueOf(
            weatherApiResponse.getWeather().get(0).getMain().toUpperCase());

        double celsius = weatherApiResponse.getMain().getTemp() - KELVIN_TEMP;

        return ResponseEntity.ok(WeatherResponse.builder()
            .weather(weather)
            .temp((int) celsius)
            .build());
    }
}
