package com.nexters.gaetteok.weather.presentation;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nexters.gaetteok.common.presentation.AbstractControllerTests;
import com.nexters.gaetteok.weather.model.MainField;
import com.nexters.gaetteok.weather.model.WeatherApiResponse;
import com.nexters.gaetteok.weather.model.WeatherField;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class WeatherControllerTest extends AbstractControllerTests {

    @Test
    void get_weather_test() throws Exception {
        // given
        WeatherField weatherField = new WeatherField("1234", "CLOUDS");
        ArrayList<WeatherField> weatherFields = new ArrayList<>();
        weatherFields.add(weatherField);
        MainField mainField = new MainField(301.0);

        WeatherApiResponse weatherApiResponse = new WeatherApiResponse(weatherFields, mainField);

        given(weatherService.getWeather(any())).willReturn(weatherApiResponse);

        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/weather")
                .param("city", "DAEGU")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
    }

}
