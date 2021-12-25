package com.project.isa.service;

import com.project.isa.model.User;
import com.project.isa.request.UserRequest;

import java.util.List;

public interface UserService {
    User findById(Long id);

    User findByEmail(String email);

    List<User> findAll();

    User register(UserRequest userRequest);
}
