package com.project.isa.service.impl;

import com.project.isa.model.Role;
import com.project.isa.model.User;
import com.project.isa.repository.UserRepository;
import com.project.isa.request.UserRequest;
import com.project.isa.service.RoleService;
import com.project.isa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User findById(Long id) throws AccessDeniedException {
        return userRepository.findById(id).orElseGet(null);
    }

    public List<User> findAll() throws AccessDeniedException {
        return userRepository.findAll();
    }

    @Override
    public User save(UserRequest userRequest) {
        User u = new User();

        u.setUsername(userRequest.getUsername());
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        u.setFirstName(userRequest.getFirstname());
        u.setLastName(userRequest.getLastname());
        u.setEnabled(true);
        u.setEmail(userRequest.getEmail());

        List<Role> roles = roleService.findByName("ROLE_USER");
        u.setRoles(roles);

        return this.userRepository.save(u);
    }
}