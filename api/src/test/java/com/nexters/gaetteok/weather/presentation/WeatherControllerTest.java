package com.nexters.gaetteok.weather.presentation;

import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.nexters.gaetteok.common.presentation.AbstractControllerTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;

class WeatherControllerTest extends AbstractControllerTests {

    @Test
    void get_weather_test() throws Exception {

        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/api/weather")
                .param("city", "daegu")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions
            .andExpect(status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document(
                "날씨 조회",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .queryParameters(
                        parameterWithName("city").description("날씨를 조회할 도시명")
                    )
                    .tag("Weather")
                    .summary("날씨 조회 API")
                    .build())
            ));
    }

}
