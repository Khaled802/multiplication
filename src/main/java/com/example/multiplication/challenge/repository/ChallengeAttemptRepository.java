package com.example.multiplication.challenge.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.multiplication.challenge.ChallengeAttempt;

public interface ChallengeAttemptRepository extends CrudRepository<ChallengeAttempt, Long> {
    public List<ChallengeAttempt> findTop10ByUserAliasOrderByIdDesc(String userAlies);
}
