package com.nexters.gaetteok.weather.presentation;

import com.nexters.gaetteok.common.presentation.AbstractControllerTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class WeatherControllerTest extends AbstractControllerTests {

    @Test
    void get_weather_test() throws Exception {
        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/weather")
                .param("city", "daegu")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
    }

}
