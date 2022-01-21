package com.project.isa.controller;

import com.project.isa.model.Adventure;
import com.project.isa.model.AdventureReservation;
import com.project.isa.request.AdventureReservationRequest;
import com.project.isa.request.CancelReservationRequest;
import com.project.isa.request.UserEmailRequest;
import com.project.isa.response.UserHistoryResponse;
import com.project.isa.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/all")
    public UserHistoryResponse getAllReservations(@RequestBody UserEmailRequest userEmailRequest) throws ParseException {
        return reservationService.getAllReservations(userEmailRequest.getEmail());
    }

    @GetMapping("/myreservations")
    @PreAuthorize("hasRole('USER')")
    public List<AdventureReservation> getMyReservations() {
        return reservationService.getMyReservations();
    }

    @GetMapping("/get/freeadventures")
    @PreAuthorize("hasRole('USER')")
    public List<Adventure> getFreeAdventures(@RequestBody AdventureReservationRequest adventureReservationRequest) {
        return reservationService.getFreeAdventures(adventureReservationRequest);
    }

    @PostMapping("/choose/adventure")
    @PreAuthorize("hasRole('USER')")
    public AdventureReservation chooseAdventure(@RequestBody AdventureReservationRequest adventureReservationRequest) {
        return reservationService.chooseAdventure(adventureReservationRequest);
    }

    @PostMapping(value = "/cancel/adventure")
    public UserHistoryResponse cancelAdventureReservation(@RequestBody CancelReservationRequest cancelReservationRequest){
            return reservationService.cancelAdventureReservation(cancelReservationRequest);
    }
    @PostMapping(value = "/cancel/vacationHome")
    public UserHistoryResponse cancelVacationHomeReservation(@RequestBody CancelReservationRequest cancelReservationRequest){
          return reservationService.cancelVacationHomeReservation(cancelReservationRequest);
    }
    @PostMapping(value = "/cancel/boat")
    public UserHistoryResponse cancelBoatReservation(@RequestBody CancelReservationRequest cancelReservationRequest){
         return reservationService.cancelBoatReservation(cancelReservationRequest);
    }
}
