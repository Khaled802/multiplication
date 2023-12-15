package com.example.multiplication.challenge.service;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.multiplication.challenge.ChallengeAttempt;
import com.example.multiplication.challenge.DTO.ChallengeAttemptDTO;
import com.example.multiplication.challenge.repository.ChallengeAttemptRepository;
import com.example.multiplication.client.AttemptClientService;
import com.example.multiplication.client.AttemptEventPublish;
import com.example.multiplication.user.User;
import com.example.multiplication.user.repository.UserRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ChallengeAttemptServiceTest {
    @Autowired
    private ChallengeAttemptService challengeAttemptService;

    @MockBean
    private ChallengeAttemptRepository challengeAttemptRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AttemptClientService attemptClientService;

    @MockBean
    private AttemptEventPublish attemptEventPublish;

    @Test
    void testVerfiyAttemptWithCorrectResult() {
        given(challengeAttemptRepository.save(any())).will(returnsFirstArg());
        given(userRepository.save(any())).will(returnsFirstArg());
        given(attemptClientService.sendAttempt(any(), any())).willReturn(true);
        doNothing().when(attemptEventPublish).publishNewAttempt(any());

        ChallengeAttemptDTO challengeAttemptDTO = new ChallengeAttemptDTO(11, 30, "Mohamed", 330);

        ChallengeAttempt result = challengeAttemptService.verfiyAttempt(challengeAttemptDTO);
        System.out.println(result);
        then(result.isCorrect()).isTrue();
        then(result.getUser().getAlias()).isEqualTo("Mohamed");
    }

    @Test
    void testVerfiyAttemptWithCorrectResultWithExistUser() {
        given(challengeAttemptRepository.save(any())).will(returnsFirstArg());
        given(userRepository.findByAlias("Mohamed")).willReturn(Optional.of(User.builder().alias("Mohamed").build()));
        given(attemptClientService.sendAttempt(any(), any())).willReturn(true);
        doNothing().when(attemptEventPublish).publishNewAttempt(any());

        ChallengeAttemptDTO challengeAttemptDTO = new ChallengeAttemptDTO(11, 30, "Mohamed", 330);

        ChallengeAttempt result = challengeAttemptService.verfiyAttempt(challengeAttemptDTO);
        System.out.println(result);
        then(result.isCorrect()).isTrue();
        then(result.getUser().getAlias()).isEqualTo("Mohamed");
    }

    @Test
    void testVerfiyAttemptWithInCorrectResult() {

        given(challengeAttemptRepository.save(any())).will(returnsFirstArg());
        given(userRepository.save(any())).will(returnsFirstArg());
        given(attemptClientService.sendAttempt(any(), any())).willReturn(true);
        doNothing().when(attemptEventPublish).publishNewAttempt(any());

        ChallengeAttemptDTO challengeAttemptDTO = new ChallengeAttemptDTO(11, 30, "Mohamed", 432);

        ChallengeAttempt result = challengeAttemptService.verfiyAttempt(challengeAttemptDTO);
        then(result.isCorrect()).isFalse();
        then(result.getUser().getAlias()).isEqualTo("Mohamed");
    }

    @Test
    void testRetriveForUser() {
        var challengeAttempts = List.of(
                new ChallengeAttempt(5L, 13, 40, User.builder().alias("Mohamed").build(), 520, true),
                new ChallengeAttempt(2L, 11, 20, User.builder().alias("Mohamed").build(), 220, true),
                new ChallengeAttempt(1L, 11, 20, User.builder().alias("Mohamed").build(), 434, false));
        given(challengeAttemptRepository.findTop10ByUserAliasOrderByIdDesc("Mohamed")).willReturn(challengeAttempts);

        var result = challengeAttemptService.getLatest("Mohamed");

        then(result.size()).isEqualTo(challengeAttempts.size());
        for (int i = 0; i < challengeAttempts.size(); i++) {
            then(result.get(i)).isEqualTo(challengeAttempts.get(i));
        }
    }
}
