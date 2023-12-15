package com.example.multiplication.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.multiplication.user.User;


public interface UserRepository extends CrudRepository<User, Long> {
    public Optional<User> findByAlias(String alias);
    // @Query("select * from User WHERE User.id in :ids")
    public List<User> findAllByIdIn(List<Long> ids);
}
