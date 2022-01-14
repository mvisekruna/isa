package com.project.isa.service;

import com.project.isa.model.BoatReservation;
import com.project.isa.request.CancelReservationRequest;
import com.project.isa.response.UserHistoryResponse;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public interface ReservationService {

    UserHistoryResponse getAllReservations(String email) throws ParseException;
    UserHistoryResponse cancelBoatReservation(CancelReservationRequest cancelReservationRequest);
    UserHistoryResponse cancelAdventureReservation(CancelReservationRequest cancelReservationRequest);
    UserHistoryResponse cancelVacationHomeReservation(CancelReservationRequest cancelReservationRequest);

}
