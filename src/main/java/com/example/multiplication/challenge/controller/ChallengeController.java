package com.example.multiplication.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.multiplication.challenge.Challenge;
import com.example.multiplication.challenge.service.ChallengeGeneratorService;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/challenges")
public class ChallengeController {
    @Autowired
    ChallengeGeneratorService challengeGeneratorService;

    @GetMapping(value="/random")
    public Challenge getChallange() {
        Challenge challenge = challengeGeneratorService.generate();
        log.info("generate random challage {}", challenge);
        return challenge;
    }
    
    
}
