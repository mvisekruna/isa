package com.project.isa.service;

import com.project.isa.model.Adventure;
import com.project.isa.model.AdventureReservation;
import com.project.isa.model.Boat;
import com.project.isa.model.BoatReservation;
import com.project.isa.request.AdventureReservationRequest;
import com.project.isa.request.BoatReservationRequest;
import com.project.isa.request.CancelReservationRequest;
import com.project.isa.response.UserHistoryResponse;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface ReservationService {

    UserHistoryResponse getAllReservations(String email) throws ParseException;
/**ADVENTURE RESERVATIONS*/
    List<AdventureReservation> findAllAdventureReservations();
    AdventureReservation findByIdAdventureReservation(Long id);
    List<AdventureReservation> getMyAdventureReservations();
    List<Adventure> getFreeAdventures(AdventureReservationRequest adventureReservationRequest) throws ParseException;
    AdventureReservation chooseAdventure(AdventureReservationRequest adventureReservationRequest);
    UserHistoryResponse cancelAdventureReservation(CancelReservationRequest cancelReservationRequest);
/**BOAT RESERVATIONS*/
    List<BoatReservation> findAllBoatReservations();
    BoatReservation findByIdBoatReservation(Long id);
    List<BoatReservation> getMyBoatReservations();
    List<Boat> getFreeBoats(BoatReservationRequest boatReservationRequest) throws ParseException;
    BoatReservation chooseBoat(BoatReservationRequest boatReservationRequest);
    UserHistoryResponse cancelBoatReservation(CancelReservationRequest cancelReservationRequest);
/**VACATION HOME RESERVATIONS*/

    UserHistoryResponse cancelVacationHomeReservation(CancelReservationRequest cancelReservationRequest);

}
