package com.example.multiplication.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.multiplication.user.User;
import com.example.multiplication.user.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getUsersByIds(List<Long> ids) {
        return userRepository.findAllByIdIn(ids);
    }
    
}
