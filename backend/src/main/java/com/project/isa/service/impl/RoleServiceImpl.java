package com.project.isa.service.impl;

import com.project.isa.model.Role;
import com.project.isa.repository.RoleRepository;
import com.project.isa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findById(Long id) {
        return this.roleRepository.getOne(id);
    }

    @Override
    public List<Role> findByName(String name) {
        return this.roleRepository.findByName(name);
    }
}
