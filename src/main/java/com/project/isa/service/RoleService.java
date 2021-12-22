package com.project.isa.service;

import com.project.isa.model.Role;

import java.util.List;

public interface RoleService {
    Role findById(Long id);
    List<Role> findByName(String name);
}
