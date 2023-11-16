package com.example.multiplication.challenge.controller;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;

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

import com.example.multiplication.challenge.ChallengeAttempt;
import com.example.multiplication.challenge.DTO.ChallengeAttemptDTO;
import com.example.multiplication.challenge.service.ChallengeAttemptService;

@WebMvcTest(controllers = ChallengeAttemptController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureJsonTesters
public class ChallengeAttemptControllerTest {
    @MockBean
    ChallengeAttemptService challengeAttemptService;

    @Autowired
    MockMvc mvc;

    @Autowired
    JacksonTester<ChallengeAttemptDTO> jsonRequest;

    @Autowired
    JacksonTester<ChallengeAttempt> jsonResponse;

    @Test
    void testAttemptDTO() throws IOException, Exception {
        given(challengeAttemptService.verfiyAttempt(new ChallengeAttemptDTO(20, 13, "Mohamed", 260)))
                .willReturn(new ChallengeAttempt(1L, 20, 30, "Mohamed", 260, true));

        MockHttpServletResponse response = mvc
                .perform(post("/attempts").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest.write(new ChallengeAttemptDTO(20, 13, "Mohamed", 260)).getJson()))
                .andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString())
                .isEqualTo(jsonResponse.write(new ChallengeAttempt(1L, 20, 30, "Mohamed", 260, true)).getJson());

    }

     @Test
    void testAttemptDTOIncorrect() throws IOException, Exception {
        given(challengeAttemptService.verfiyAttempt(new ChallengeAttemptDTO(20, 13, "Mohamed", 434)))
                .willReturn(new ChallengeAttempt(1L, 20, 30, "Mohamed", 434, false));

        MockHttpServletResponse response = mvc
                .perform(post("/attempts").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest.write(new ChallengeAttemptDTO(20, 13, "Mohamed", 434)).getJson()))
                .andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString())
                .isEqualTo(jsonResponse.write(new ChallengeAttempt(1L, 20, 30, "Mohamed", 434, false)).getJson());

    }


    @Test
    void testAttemptDTOWithInvalidDTO() throws IOException, Exception {
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(100, 43, "Mohamed", 4300);
        given(challengeAttemptService.verfiyAttempt(attemptDTO)).willReturn(new ChallengeAttempt(1L, 100, 42, "Mohamed", 4300, true));

        var response = mvc.perform(post("/attempts").contentType(MediaType.APPLICATION_JSON).content(jsonRequest.write(attemptDTO).getJson())).andReturn().getResponse();
        
        then(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    
}
