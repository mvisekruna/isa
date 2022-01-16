package com.project.isa.repository;

import com.project.isa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
   User findByEmail(String email);


}
