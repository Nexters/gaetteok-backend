package com.nexters.gaetteok.common.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexters.gaetteok.image.service.ImageUploader;
import com.nexters.gaetteok.user.application.FriendApplication;
import com.nexters.gaetteok.user.application.UserApplication;
import com.nexters.gaetteok.walklog.application.CommentApplication;
import com.nexters.gaetteok.walklog.application.ReactionApplication;
import com.nexters.gaetteok.walklog.application.WalkLogApplication;
import com.nexters.gaetteok.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class AbstractControllerTests {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

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
    protected ImageUploader imageUploader;

    @MockBean
    protected WeatherService weatherService;
}
