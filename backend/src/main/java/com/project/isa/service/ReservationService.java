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

    List<AdventureReservation> findAll();
    AdventureReservation findById(Long id);
    UserHistoryResponse getAllReservations(String email) throws ParseException;
    List<AdventureReservation> getMyReservations();
    List<Adventure> getFreeAdventures(AdventureReservationRequest adventureReservationRequest);
    AdventureReservation chooseAdventure(AdventureReservationRequest adventureReservationRequest);
    UserHistoryResponse cancelBoatReservation(CancelReservationRequest cancelReservationRequest);
    UserHistoryResponse cancelAdventureReservation(CancelReservationRequest cancelReservationRequest);
    UserHistoryResponse cancelVacationHomeReservation(CancelReservationRequest cancelReservationRequest);

}
