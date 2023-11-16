package com.example.multiplication.challenge.service;

import org.springframework.stereotype.Service;

import com.example.multiplication.challenge.ChallengeAttempt;
import com.example.multiplication.challenge.DTO.ChallengeAttemptDTO;
import com.example.multiplication.user.User;

@Service
public class ChallengeAttemptServiceImp implements ChallengeAttemptService {

    @Override
    public ChallengeAttempt verfiyAttempt(ChallengeAttemptDTO attemptDTO) {
        User user = new User(null, attemptDTO.getUser());
        int result = attemptDTO.getFactor1() * attemptDTO.getFactor2();
        boolean correct = attemptDTO.getGuess() == result;
        ChallengeAttempt challengeAttempt = new ChallengeAttempt(null, attemptDTO.getFactor1(), attemptDTO.getFactor2(),
                attemptDTO.getUser(), result, correct);
        return challengeAttempt;
    }

}
