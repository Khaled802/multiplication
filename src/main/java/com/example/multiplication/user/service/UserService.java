package com.example.multiplication.user.service;

import java.util.List;

import com.example.multiplication.user.User;

public interface UserService {
    List<User> getUsersByIds(List<Long> ids);
}
