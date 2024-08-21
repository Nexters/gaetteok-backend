package com.nexters.gaetteok.user.presentation.response;

import com.nexters.gaetteok.domain.User;
import com.nexters.gaetteok.weather.enums.City;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateUserLocationResponse {

    @Schema(description = "변경된 위치 정보", example = "SEOUL")
    private final City location;

    @Builder
    public UpdateUserLocationResponse(City location) {
        this.location = location;
    }

    public static UpdateUserLocationResponse of(User user) {
        return UpdateUserLocationResponse.builder()
            .location(City.valueOf(user.getLocation()))
            .build();
    }
}
