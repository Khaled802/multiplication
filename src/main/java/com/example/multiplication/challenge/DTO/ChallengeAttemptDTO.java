package com.example.multiplication.challenge.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Value;

@Value
public class ChallengeAttemptDTO {
    @Min(11) @Max(99)
    int factor1, factor2;
    @NotBlank
    String user;
    @Positive
    int guess;
}
