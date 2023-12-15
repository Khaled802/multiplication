package com.example.multiplication.client.DTO;

import lombok.Value;

@Value
public class AttemptEvent {
    Long id;
    String userAlies;
    Long userId;
    int factor1;
    int factor2;
    boolean correct;
}
