package com.example.multiplication.challenge.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.multiplication.challenge.Challenge;

@Service
public class ChallengeGeneratorServiceImp implements ChallengeGeneratorService {
    private final Random random;
    private final int MAX_VALUE = 99;
    private final int MIN_VALUE = 11;

    public ChallengeGeneratorServiceImp(Random random) {
        this.random = random;
    }

    public ChallengeGeneratorServiceImp() {
        this.random = new Random();
    }

    @Override
    public Challenge generate() {
        int factor1 = random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;        
        int factor2 = random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;

        return new Challenge(factor1, factor2);
    }
    
}
