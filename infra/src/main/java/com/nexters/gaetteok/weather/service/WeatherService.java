package com.nexters.gaetteok.weather.service;

import com.nexters.gaetteok.weather.config.WeatherConfig;
import com.nexters.gaetteok.weather.enums.City;
import com.nexters.gaetteok.weather.model.WeatherApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final RestClient restClient;
    private final WeatherConfig weatherConfig;

    public WeatherApiResponse getWeather(City city) throws RuntimeException {

        WeatherApiResponse weatherApiResponse = restClient.get()
            .uri(
                weatherConfig.getApiPath() + "?lat={lat}&lon={lon}&appid={apiKey}", city.getLat(),
                city.getLon(), weatherConfig.getApiKey()
            )
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body(WeatherApiResponse.class);

        if (weatherApiResponse == null) {
            throw new RuntimeException("Api response should not be null");
        }

        return weatherApiResponse;
    }

}
