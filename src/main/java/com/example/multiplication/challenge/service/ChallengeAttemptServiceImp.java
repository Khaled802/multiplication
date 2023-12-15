package com.example.multiplication.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.multiplication.challenge.ChallengeAttempt;
import com.example.multiplication.challenge.DTO.ChallengeAttemptDTO;
import com.example.multiplication.challenge.repository.ChallengeAttemptRepository;
import com.example.multiplication.client.AttemptClientService;
import com.example.multiplication.client.AttemptEventPublish;
import com.example.multiplication.client.DTO.AttemptEvent;
import com.example.multiplication.user.User;
import com.example.multiplication.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChallengeAttemptServiceImp implements ChallengeAttemptService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChallengeAttemptRepository challengeAttemptRepository;

    @Autowired
    private AttemptEventPublish attemptEventPublish;

    @Transactional
    @Override
    public ChallengeAttempt verfiyAttempt(ChallengeAttemptDTO attemptDTO) {
        User user = userRepository.findByAlias(attemptDTO.getUser())
            .orElseGet(()-> {
                return userRepository.save(User.builder().alias(attemptDTO.getUser()).build());
            });
        int result = attemptDTO.getFactor1() * attemptDTO.getFactor2();
        boolean correct = attemptDTO.getGuess() == result;
        ChallengeAttempt challengeAttempt = ChallengeAttempt.builder()
                .factor1(attemptDTO.getFactor1()).factor2(attemptDTO.getFactor2()).user(user).resultAttempt(attemptDTO.getGuess())
                .correct(correct).build();
        ChallengeAttempt resultChallengeAttempt = challengeAttemptRepository.save(challengeAttempt);

        attemptEventPublish.publishNewAttempt(resultChallengeAttempt);

        return resultChallengeAttempt;
    }

    @Override
    public List<ChallengeAttempt> getLatest(String user) {
        return challengeAttemptRepository.findTop10ByUserAliasOrderByIdDesc(user);
    }

}
