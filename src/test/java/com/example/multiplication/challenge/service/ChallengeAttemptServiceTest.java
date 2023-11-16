package com.example.multiplication.challenge.service;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.multiplication.challenge.ChallengeAttempt;
import com.example.multiplication.challenge.DTO.ChallengeAttemptDTO;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ChallengeAttemptServiceTest {
    @Autowired
    private ChallengeAttemptService challengeAttemptService;

    
    @Test
    void testVerfiyAttemptWithCorrectResult() {
        ChallengeAttemptDTO challengeAttemptDTO = new ChallengeAttemptDTO(11, 30, "Mohamed", 330);

        ChallengeAttempt challengeAttempt = challengeAttemptService.verfiyAttempt(challengeAttemptDTO);
        then(challengeAttempt.isCorrect()).isTrue();

    }

    @Test
    void testVerfiyAttemptWithInCorrectResult() {
        ChallengeAttemptDTO challengeAttemptDTO = new ChallengeAttemptDTO(11, 30, "Mohamed", 432);

        ChallengeAttempt challengeAttempt = challengeAttemptService.verfiyAttempt(challengeAttemptDTO);
        then(challengeAttempt.isCorrect()).isFalse();

    }   
}
