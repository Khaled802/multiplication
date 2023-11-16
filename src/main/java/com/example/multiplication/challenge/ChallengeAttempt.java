package com.example.multiplication.challenge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ChallengeAttempt {
    private Long id;
    private int factor1;
    private int factor2;
    private String user;
    private int resultAttempt;
    private boolean correct;

    public Boolean isCorrect() {
        return correct;
    }
}
