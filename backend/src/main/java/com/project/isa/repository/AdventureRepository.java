package com.project.isa.repository;

import com.project.isa.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventureRepository extends JpaRepository<Adventure, Long> {
}
