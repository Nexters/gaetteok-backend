package com.nexters.gaetteok.common.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexters.gaetteok.common.application.AdminApplication;
import com.nexters.gaetteok.firebase.service.PushNotificationService;
import com.nexters.gaetteok.image.service.ImageUploader;
import com.nexters.gaetteok.jwt.JwtTokenValidator;
import com.nexters.gaetteok.jwt.UserInfo;
import com.nexters.gaetteok.persistence.repository.UserRepository;
import com.nexters.gaetteok.user.application.AuthApplication;
import com.nexters.gaetteok.user.application.FriendApplication;
import com.nexters.gaetteok.user.application.UserApplication;
import com.nexters.gaetteok.walklog.application.CommentApplication;
import com.nexters.gaetteok.walklog.application.ReactionApplication;
import com.nexters.gaetteok.walklog.application.WalkLogApplication;
import com.nexters.gaetteok.weather.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@WebMvcTest
public class AbstractControllerTests {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected AuthApplication authApplication;

    @MockBean
    protected UserApplication userApplication;

    @MockBean
    protected FriendApplication friendApplication;

    @MockBean
    protected WalkLogApplication walkLogApplication;

    @MockBean
    protected CommentApplication commentApplication;

    @MockBean
    protected ReactionApplication reactionApplication;

    @MockBean
    protected JwtTokenValidator jwtTokenValidator;

    @MockBean
    protected ImageUploader imageUploader;

    @MockBean
    protected WeatherService weatherService;

    @MockBean
    protected UserRepository userRepository;

    @MockBean
    protected PushNotificationService pushNotificationService;

    @MockBean
    protected AdminApplication adminApplication;

    @BeforeEach
    void mockingTokenValidate() {
        given(jwtTokenValidator.decodeToken(anyString()))
            .willReturn(UserInfo.builder().userId(1L).build());
        given(userRepository.existsById(anyLong()))
            .willReturn(true);
    }

}
