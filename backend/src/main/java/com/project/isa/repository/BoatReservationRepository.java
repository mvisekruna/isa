package com.project.isa.repository;

import com.project.isa.model.BoatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoatReservationRepository extends JpaRepository<BoatReservation, Long> {

    List<BoatReservation> findBoatReservationsByUserId(Long id);
}
