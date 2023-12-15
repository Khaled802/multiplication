package com.example.multiplication.client;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.multiplication.challenge.ChallengeAttempt;
import com.example.multiplication.client.DTO.AttemptEvent;

@Service
public class AttemptEventPublish {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${amqp.exchange.attempts}")
    private String exchange;

    public void publishNewAttempt(ChallengeAttempt challengeAttempt) {
        AttemptEvent attemptEvent = mapToAttemptEvent(challengeAttempt);
        String routingKey ="attempt." + (challengeAttempt.isCorrect() ? "correct": "wrong");
        amqpTemplate.convertAndSend(exchange, routingKey, attemptEvent);
    }

    public static AttemptEvent mapToAttemptEvent(ChallengeAttempt challengeAttempt) {
        return new AttemptEvent(challengeAttempt.getId(), challengeAttempt.getUser().getAlias(),
                challengeAttempt.getUser().getId(), challengeAttempt.getFactor1(), challengeAttempt.getFactor2(),
                challengeAttempt.isCorrect());
    }
}
