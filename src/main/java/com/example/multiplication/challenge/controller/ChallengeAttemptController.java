package com.example.multiplication.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.multiplication.challenge.ChallengeAttempt;
import com.example.multiplication.challenge.DTO.ChallengeAttemptDTO;
import com.example.multiplication.challenge.service.ChallengeAttemptService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RestController
@RequestMapping("/attempts")
public class ChallengeAttemptController {
    @Autowired
    ChallengeAttemptService challengeAttemptService;

    @PostMapping(value="")
    public ChallengeAttempt attemptDTO(@Valid @RequestBody ChallengeAttemptDTO entity) {
        ChallengeAttempt challengeAttempt = challengeAttemptService.verfiyAttempt(entity);
        log.info("challage attempt {}", challengeAttempt);
        return challengeAttempt;
    }
    
}
