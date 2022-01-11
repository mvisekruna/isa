package com.project.isa.repository;

import com.project.isa.model.AdventureReservation;
import com.project.isa.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventureReservationRepository extends JpaRepository<AdventureReservation, Long> {
}
