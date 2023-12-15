package com.example.multiplication.user.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.multiplication.user.User;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    List<User> users;

    @BeforeEach
    void setUp() {
        users = List.of();
        users = List.of(
                new User(1L, "Mohamed"),
                new User(2L, "Khaled"),
                new User(3L, "Emad"),
                new User(4L, "Ahmed")
            );
        userRepository.saveAll(users);
    }

    @AfterEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    void testFindAllByIdIn() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(3L);

        List<User> result = userRepository.findAllByIdIn(ids);


        then(result.size()).isEqualTo(ids.size());

        List<Long> resultIds = new ArrayList<>();
        for (User resultUser : result) {
            resultIds.add(resultUser.getId());
        }
        ids.sort(Long::compareTo);
        resultIds.sort(Long::compareTo);
        then(resultIds.toString()).isEqualTo(ids.toString());
    }
}
