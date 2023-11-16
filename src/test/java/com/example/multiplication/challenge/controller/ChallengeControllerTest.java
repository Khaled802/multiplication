package com.example.multiplication.challenge.controller;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.example.multiplication.challenge.Challenge;
import com.example.multiplication.challenge.service.ChallengeGeneratorService;

@WebMvcTest(controllers = ChallengeController.class)
@AutoConfigureJsonTesters
@ExtendWith(MockitoExtension.class)
public class ChallengeControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    ChallengeGeneratorService challengeGeneratorService;

    @Autowired
    private JacksonTester<Challenge> jsonChallengeResponse;

    @Test
    void testGetChallange() throws Exception {
        given(challengeGeneratorService.generate()).willReturn(new Challenge(12, 20));

        MockHttpServletResponse response = mvc.perform(get("/challenges/random").contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(jsonChallengeResponse.write(new Challenge(12, 20)).getJson());
    }
}
