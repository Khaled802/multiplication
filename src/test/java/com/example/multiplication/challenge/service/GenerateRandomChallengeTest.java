package com.example.multiplication.challenge.service;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.multiplication.challenge.Challenge;

// @SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GenerateRandomChallengeTest {
    @Spy
    private Random random;

    ChallengeGeneratorService generateRandomChallenge;

    @BeforeEach
    void setUp() {
        generateRandomChallenge = new ChallengeGeneratorServiceImp(random);
    }

    @Test
    void testGenerate() {
        given(random.nextInt(89)).willReturn(20, 30);

        Challenge challenge = generateRandomChallenge.generate();
        then(challenge).isEqualTo(new Challenge(31, 41));
    }
}
