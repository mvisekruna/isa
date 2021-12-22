package com.project.isa.service;

import com.project.isa.model.User;
import com.project.isa.request.UserRequest;

import java.util.List;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
    User save(UserRequest userRequest);
}
