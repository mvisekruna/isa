package com.project.isa.service;

import com.project.isa.response.UserHistoryResponse;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public interface ReservationService {

    UserHistoryResponse getAllReservations(String email) throws ParseException;
}
