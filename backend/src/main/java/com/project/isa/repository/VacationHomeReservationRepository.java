package com.project.isa.repository;

import com.project.isa.model.VacationHomeReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationHomeReservationRepository extends JpaRepository<VacationHomeReservation, Long> {

    List<VacationHomeReservation> findVacationHomeReservationsByUserId(Long id);

}
