package com.nexters.gaetteok.user.presentation.response;

import com.nexters.gaetteok.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateUserLocationResponse {

    private final String location;

    @Builder
    public UpdateUserLocationResponse(String location) {
        this.location = location;
    }

    public static UpdateUserLocationResponse of(User user) {
        return UpdateUserLocationResponse.builder()
            .location(user.getLocation())
            .build();
    }
}
