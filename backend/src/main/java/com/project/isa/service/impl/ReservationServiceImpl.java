package com.project.isa.service.impl;

import com.project.isa.model.AdventureReservation;
import com.project.isa.model.User;
import com.project.isa.model.VacationHomeReservation;
import com.project.isa.repository.UserRepository;
import com.project.isa.response.AdventureReservationResponse;
import com.project.isa.response.BoatReservationResponse;
import com.project.isa.model.BoatReservation;
import com.project.isa.repository.AdventureReservationRepository;
import com.project.isa.repository.BoatReservationRepository;
import com.project.isa.repository.VacationHomeReservationRepository;
import com.project.isa.response.UserHistoryResponse;
import com.project.isa.response.VacationHomeReservationResponse;
import com.project.isa.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private BoatReservationRepository boatReservationRepository;

    @Autowired
    private VacationHomeReservationRepository vacationHomeReservationRepository;

    @Autowired
    private AdventureReservationRepository adventureReservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserHistoryResponse getAllReservations(String email) throws ParseException {
        User user = this.userRepository.findByEmail(email);
        List<BoatReservationResponse> boatReservationResponses = new ArrayList<>();
        List<VacationHomeReservationResponse> vacationHomeReservationResponses = new ArrayList<>();
        List<AdventureReservationResponse> adventureReservationResponses = new ArrayList<>();

        UserHistoryResponse userHistoryResponse = new UserHistoryResponse();
        List<BoatReservation> boatReservations = boatReservationRepository.findBoatReservationsByUserId(user.getId());
        List<VacationHomeReservation> vacationHomeReservations = vacationHomeReservationRepository.findVacationHomeReservationsByUserId(user.getId());
        List<AdventureReservation> adventureReservations = adventureReservationRepository.findAdventureReservationsByUserId(user.getId());

        for(BoatReservation boatReservation: boatReservations) {
            BoatReservationResponse boatReservationResponse = new BoatReservationResponse(boatReservation);
            boatReservationResponses.add(boatReservationResponse);
        }

        for(VacationHomeReservation vacationHomeReservation: vacationHomeReservations) {
            VacationHomeReservationResponse vacationHomeReservationResponse = new VacationHomeReservationResponse(vacationHomeReservation);
            vacationHomeReservationResponses.add(vacationHomeReservationResponse);
        }

        for(AdventureReservation adventureReservation: adventureReservations) {
            AdventureReservationResponse adventureReservationResponse = new AdventureReservationResponse(adventureReservation);
            adventureReservationResponses.add(adventureReservationResponse);
        }
        userHistoryResponse.setBoatReservations(boatReservationResponses);
        userHistoryResponse.setAdventureReservations(adventureReservationResponses);
        userHistoryResponse.setVacationHomeReservations(vacationHomeReservationResponses);
        return userHistoryResponse;
    }
}
