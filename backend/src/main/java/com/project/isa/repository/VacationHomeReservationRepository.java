package com.project.isa.repository;

import com.project.isa.model.Role;
import com.project.isa.model.VacationHomeReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationHomeReservationRepository extends JpaRepository<VacationHomeReservation, Long> {
}
