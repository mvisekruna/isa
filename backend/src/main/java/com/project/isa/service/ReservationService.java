package com.project.isa.service;

import com.project.isa.response.UserHistoryResponse;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService {

    UserHistoryResponse getAllReservations(Long userId);
}
