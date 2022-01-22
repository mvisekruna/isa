package com.project.isa.controller;

import com.project.isa.model.Adventure;
import com.project.isa.model.AdventureReservation;
import com.project.isa.model.Boat;
import com.project.isa.model.BoatReservation;
import com.project.isa.request.AdventureReservationRequest;
import com.project.isa.request.BoatReservationRequest;
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

    /**ADVENTURE RESERVATIONS************************/

    @GetMapping("/findall/adventurereservations")
    public List<AdventureReservation> findAllAdventureReservations() {
        return reservationService.findAllAdventureReservations();
    }

    @GetMapping("/findone/adventurereservation/{id}")
    public AdventureReservation findByIdAdventureReservation(@PathVariable Long id) {
        return reservationService.findByIdAdventureReservation(id);
    }

    @GetMapping("/myadventuere/reservations")
    @PreAuthorize("hasRole('USER')")
    public List<AdventureReservation> getMyAdventureReservations() {
        return reservationService.getMyAdventureReservations();
    }

    @PostMapping("/get/freeadventures")
    @PreAuthorize("hasRole('USER')")
    public List<Adventure> getFreeAdventures(@RequestBody AdventureReservationRequest adventureReservationRequest) throws ParseException {
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

    /**BOAT RESERVATIONS************************/

    @GetMapping("/findall/boatreservations")
    public List<BoatReservation> findAllBoatReservations() {
        return reservationService.findAllBoatReservations();
    }

    @GetMapping("/findone/boatreservation/{id}")
    public BoatReservation findByIdBoatReservation(@PathVariable Long id) {
        return reservationService.findByIdBoatReservation(id);
    }

    @GetMapping("/myboat/reservations")
    @PreAuthorize("hasRole('USER')")
    public List<BoatReservation> getMyBoatReservations(){
        return reservationService.getMyBoatReservations();
    }

    @GetMapping("/get/freeboats")
    @PreAuthorize("hasRole('USER')")
    public List<Boat> getFreeBoats(@RequestBody BoatReservationRequest boatReservationRequest) throws ParseException {
        return reservationService.getFreeBoats(boatReservationRequest);
    }

    @PostMapping("/choose/boat")
    @PreAuthorize("hasRole('USER')")
    public BoatReservation chooseBoat(BoatReservationRequest boatReservationRequest) {
        return reservationService.chooseBoat(boatReservationRequest);
    }

    @PostMapping(value = "/cancel/boat")
    public UserHistoryResponse cancelBoatReservation(@RequestBody CancelReservationRequest cancelReservationRequest){
        return reservationService.cancelBoatReservation(cancelReservationRequest);
    }

    /**VACATION HOME RESERVATIONS************************/

    @PostMapping(value = "/cancel/vacationHome")
    public UserHistoryResponse cancelVacationHomeReservation(@RequestBody CancelReservationRequest cancelReservationRequest){
          return reservationService.cancelVacationHomeReservation(cancelReservationRequest);
    }

}
