package com.example.multiplication.challenge.controller;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;
import java.util.List;

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
import com.example.multiplication.user.User;

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

    @Autowired
    JacksonTester<List<ChallengeAttempt>> jsonResponseList;

    @Test
    void testAttemptDTO() throws IOException, Exception {
        given(challengeAttemptService.verfiyAttempt(new ChallengeAttemptDTO(20, 13, "Mohamed", 260)))
                .willReturn(new ChallengeAttempt(null, 20, 30, User.builder().alias("Mohamed").build(),
                        260, true));

        MockHttpServletResponse response = mvc
                .perform(post("/attempts").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest
                                .write(new ChallengeAttemptDTO(20, 13, "Mohamed", 260))
                                .getJson()))
                .andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString())
                .isEqualTo(jsonResponse
                        .write(new ChallengeAttempt(null, 20, 30,
                                User.builder().alias("Mohamed").build(),
                                260, true))
                        .getJson());

    }

    @Test
    void testAttemptDTOIncorrect() throws IOException, Exception {
        given(challengeAttemptService.verfiyAttempt(new ChallengeAttemptDTO(20, 13, "Mohamed", 434)))
                .willReturn(new ChallengeAttempt(1L, 20, 30, User.builder().alias("Mohamed").build(), 434, false));

        MockHttpServletResponse response = mvc
                .perform(post("/attempts").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest
                                .write(new ChallengeAttemptDTO(20, 13, "Mohamed", 434))
                                .getJson()))
                .andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString())
                .isEqualTo(jsonResponse
                        .write(new ChallengeAttempt(1L, 20, 30, User.builder().alias("Mohamed").build(), 434, false))
                        .getJson());

    }

    @Test
    void testAttemptDTOWithInvalidDTO() throws IOException, Exception {
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(100, 43, "Mohamed", 4300);
        given(challengeAttemptService.verfiyAttempt(attemptDTO))
                .willReturn(new ChallengeAttempt(1L, 100, 42, User.builder().alias("Mohamed").build(), 4300, true));

        var response = mvc
                .perform(post("/attempts").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest.write(attemptDTO).getJson()))
                .andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void  testGetLatestAttempts() throws Exception {
        List<ChallengeAttempt> challengeAttempts = List.of(
            new ChallengeAttempt(5L, 20, 20, new User(1L, "Mohamed"), 400, true),
            new ChallengeAttempt(2L, 23, 45, new User(1L, "Mohamed"), 1035, true),
            new ChallengeAttempt(1L, 23, 45, new User(1L, "Mohamed"), 1433, false)          
        );
        given(challengeAttemptService.getLatest("Mohamed")).willReturn(challengeAttempts);

        var response = mvc.perform(get("/attempts/"+"Mohamed").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(jsonResponseList.write(challengeAttempts).getJson());
    }


    @Test
    void  testGetLatestAttemptsWithNoAttempts() throws Exception {
        List<ChallengeAttempt> challengeAttempts = List.of();
        given(challengeAttemptService.getLatest("Mohamed")).willReturn(challengeAttempts);

        var response = mvc.perform(get("/attempts/"+"Mohamed").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(jsonResponseList.write(challengeAttempts).getJson());
    }

}
