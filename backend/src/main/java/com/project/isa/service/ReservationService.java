package com.project.isa.service;

import com.project.isa.model.Adventure;
import com.project.isa.model.AdventureReservation;
import com.project.isa.request.AdventureReservationRequest;
import com.project.isa.request.CancelReservationRequest;
import com.project.isa.response.UserHistoryResponse;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface ReservationService {

    UserHistoryResponse getAllReservations(String email) throws ParseException;
    List<Adventure> getFreeAdventures(AdventureReservationRequest adventureReservationRequest);
    UserHistoryResponse cancelBoatReservation(CancelReservationRequest cancelReservationRequest);
    UserHistoryResponse cancelAdventureReservation(CancelReservationRequest cancelReservationRequest);
    UserHistoryResponse cancelVacationHomeReservation(CancelReservationRequest cancelReservationRequest);

}
