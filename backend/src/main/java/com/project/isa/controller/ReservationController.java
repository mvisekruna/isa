package com.project.isa.controller;

import com.project.isa.response.UserHistoryResponse;
import com.project.isa.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ReservationController {


    @Autowired
    private ReservationService reservationService;

    @GetMapping("/{userId}")
    public UserHistoryResponse getAllReservations(@PathVariable Long userId) {
        return reservationService.getAllReservations(userId);
    }
}
