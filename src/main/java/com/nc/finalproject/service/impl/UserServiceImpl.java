package com.nc.finalproject.service.impl;

import com.nc.finalproject.model.Role;
import com.nc.finalproject.model.User;
import com.nc.finalproject.repository.RoleRepository;
import com.nc.finalproject.repository.UserRepository;
import com.nc.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service class for operations with entity {@link User}.
 * Implementation of {@link UserService}.
 *
 * @author WildDed
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return userRepository.findById(aLong);
    }

    @Override
    public String findRoleByUsername(String username) {
        return userRepository.findRoleByUsername(username);
    }

    @Override
    public List<User> findDoctors() {
        return userRepository.findDoctors();
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }

    @Override
    public User saveAndFlushDoctor(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(3L));
        user.setRoles(roles);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User saveAndFlush(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1L));
        user.setRoles(roles);
        return userRepository.saveAndFlush(user);
    }
}
