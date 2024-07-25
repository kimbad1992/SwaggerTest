package org.study.swaggertest.mockito;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.study.swaggertest.controller.UserController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class MockMvc_테스트 {

    @SpyBean
    UserController userController;

    MockMvc mockMvc;

//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//    }

    @Test
    void 가짜_호출() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/v1/users"));

        log.info("Result :: {}", resultActions.andReturn().getResponse().getContentAsString());
        resultActions.andExpect(result -> result.getResponse().getContentLength());
    }
}
