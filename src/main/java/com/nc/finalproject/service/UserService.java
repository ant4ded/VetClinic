package com.nc.finalproject.service;

import com.nc.finalproject.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface for operations with entity {@link User} and associated table in the database.
 *
 * @author WildDed
 * @version 1.0
 */

public interface UserService {
    void deleteByUsername(String username);

    User findByUsername(String username);

    List<User> findAll();

    Optional<User> findById(Long aLong);

    String findRoleByUsername(String username);

    List<User> findDoctors();

    void deleteById(Long aLong);

    User saveAndFlushDoctor(User user);

    User saveAndFlush(User user);
}
