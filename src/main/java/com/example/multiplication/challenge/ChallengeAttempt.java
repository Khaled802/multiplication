package com.example.multiplication.challenge;

import com.example.multiplication.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChallengeAttempt {
    
    @Id
    @GeneratedValue
    private Long id;
    private int factor1;
    private int factor2;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_id")
    // @JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer"})
    @JsonIgnore
    private User user;
    private int resultAttempt;
    private boolean correct;

    public Boolean isCorrect() {
        return correct;
    }
}
