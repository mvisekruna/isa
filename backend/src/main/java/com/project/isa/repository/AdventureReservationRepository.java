package com.project.isa.repository;

import com.project.isa.model.AdventureReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdventureReservationRepository extends JpaRepository<AdventureReservation, Long> {

    List<AdventureReservation> findAdventureReservationsByUserId(Long id);

}
