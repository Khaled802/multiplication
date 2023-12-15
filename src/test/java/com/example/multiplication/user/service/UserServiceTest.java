package com.example.multiplication.user.service;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.multiplication.user.User;
import com.example.multiplication.user.repository.UserRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Test
    void testGetUsersByIds() {
        Map<Long, User> users = Map.of(
            1L, new User(1L, "Mohamed"),
            3L, new User(3L, "Emad")
        );
        given(userRepository.findAllByIdIn(List.of(1L, 3L))).willReturn(users.values().stream().toList());

        List<User> result= userService.getUsersByIds(List.of(1L, 3L));

        then(result.size()).isEqualTo(2);

        for (User resultUser : result) {
            then(resultUser).isEqualTo(users.get(resultUser.getId()));
        }
    }
}
