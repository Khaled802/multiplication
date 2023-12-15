package com.example.multiplication.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.multiplication.user.User;
import com.example.multiplication.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{idList}")
    private List<User> getUsersByIds(@PathVariable final List<Long> idList) {
        log.info("ids = [{}]", idList);
        List<User> users = userService.getUsersByIds(idList);
        return users;
    }
}
