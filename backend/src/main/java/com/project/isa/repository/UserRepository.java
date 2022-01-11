package com.project.isa.repository;

import com.project.isa.model.Role;
import com.project.isa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
   User findByEmail(String email);


}
