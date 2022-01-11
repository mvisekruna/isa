package com.project.isa.service.impl;

import com.project.isa.dto.BoatReservationDto;
import com.project.isa.model.BoatReservation;
import com.project.isa.repository.AdventureReservationRepository;
import com.project.isa.repository.BoatReservationRepository;
import com.project.isa.repository.VacationHomeReservationRepository;
import com.project.isa.response.UserHistoryResponse;
import com.project.isa.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public UserHistoryResponse getAllReservations(Long userId) {
        List<BoatReservationDto> boatReservationDtos = new ArrayList<>();
        UserHistoryResponse userHistoryResponse = new UserHistoryResponse();
        List<BoatReservation> boatReservations = boatReservationRepository.findBoatReservationsByUserId(userId);

        for(BoatReservation boatReservation: boatReservations) {
            BoatReservationDto boatReservationDto = new BoatReservationDto(boatReservation);
            boatReservationDtos.add(boatReservationDto);
        }
        userHistoryResponse.setBoatReservations(boatReservationDtos);
        return userHistoryResponse;
    }
}
