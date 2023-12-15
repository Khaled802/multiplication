package com.example.multiplication.user.controller;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.example.multiplication.user.User;
import com.example.multiplication.user.service.UserService;

@WebMvcTest(controllers = UserController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureJsonTesters
public class UserControllerTest {
    @MockBean
    UserService userService;

    @Autowired
    MockMvc mvc;

    @Autowired
    JacksonTester<List<User>> jsonResponse;

    @Test
    void testGetUsersByIds() throws Exception {
        List<Long> ids = List.of(1L, 3L);
        List<User> users = List.of(new User(1L, "Mohamed"), new User(3L, "Emad"));
        given(userService.getUsersByIds(ids)).willReturn(users);

        MockHttpServletResponse response = mvc.perform(get("/users/" + String.join(",", ids.stream().map(String::valueOf).toList()))
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(jsonResponse.write(users).getJson());
    }

    @Test
    void testGetUsersByIdsWithNoMatchValues() throws Exception {
        List<Long> ids = List.of(9L);
        List<User> users = List.of();
        given(userService.getUsersByIds(ids)).willReturn(users);

        MockHttpServletResponse response = mvc.perform(get("/users/" + String.join(",", ids.stream().map(String::valueOf).toList()))
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(jsonResponse.write(users).getJson());
    }
}
