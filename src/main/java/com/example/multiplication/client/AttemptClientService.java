package com.example.multiplication.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.multiplication.challenge.ChallengeAttempt;
import com.example.multiplication.client.DTO.AttemptEvent;

@Service
public class AttemptClientService {

    private RestTemplate restTemplate;

    public AttemptClientService(final RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    private final String host = "http://localhost:8081";

    public boolean sendAttempt(ChallengeAttempt challengeAttempt, Long userId) {
        AttemptEvent attemptDTO = new AttemptEvent(challengeAttempt.getId(), challengeAttempt.getUser().getAlias(), userId,
                challengeAttempt.getFactor1(), challengeAttempt.getFactor2(), challengeAttempt.isCorrect());
        ResponseEntity<AttemptEvent> responseEntity = restTemplate.postForEntity(host+"/attempts", attemptDTO, AttemptEvent.class, "");
        return responseEntity.getStatusCode().is2xxSuccessful();
    }
}
