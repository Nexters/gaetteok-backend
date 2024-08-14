package com.nexters.gaetteok.common.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexters.gaetteok.image.service.ImageUploader;
import com.nexters.gaetteok.user.application.FriendApplication;
import com.nexters.gaetteok.user.application.UserApplication;
import com.nexters.gaetteok.walklog.application.WalkLogApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@WebMvcTest
@ExtendWith(RestDocumentationExtension.class)
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
    protected ImageUploader imageUploader;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext,
               RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

}
