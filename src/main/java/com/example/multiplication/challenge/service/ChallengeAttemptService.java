package com.example.multiplication.challenge.service;

import java.util.List;

import com.example.multiplication.challenge.ChallengeAttempt;
import com.example.multiplication.challenge.DTO.ChallengeAttemptDTO;


public interface ChallengeAttemptService {
    ChallengeAttempt verfiyAttempt(ChallengeAttemptDTO attemptDTO);
    List<ChallengeAttempt> getLatest(String user);
}
