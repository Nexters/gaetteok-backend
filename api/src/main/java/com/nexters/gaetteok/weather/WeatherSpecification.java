package com.nexters.gaetteok.weather;

import com.nexters.gaetteok.weather.presentation.response.WeatherResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Weather", description = "날씨 조회 API")
public interface WeatherSpecification {

    @Operation(summary = "날씨 조회", description = "도시명으로 날씨를 조회하는 API")
    @ApiResponse(
        responseCode = "200",
        description = "날씨 조회 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = WeatherResponse.class)
        )
    )
    ResponseEntity<WeatherResponse> getWeather(
        @Parameter(description = "날씨를 조회할 도시명") String city
    );

}
