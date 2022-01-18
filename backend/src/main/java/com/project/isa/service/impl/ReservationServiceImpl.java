package com.project.isa.service.impl;

import com.project.isa.enumeration.ReservationStatus;
import com.project.isa.model.AdventureReservation;
import com.project.isa.model.User;
import com.project.isa.model.VacationHomeReservation;
import com.project.isa.repository.UserRepository;
import com.project.isa.request.AdventureReservationRequest;
import com.project.isa.request.CancelReservationRequest;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public UserHistoryResponse getAllReservations(String email) {
        User user = this.userRepository.findByEmail(email);
        List<BoatReservationResponse> boatReservationResponses = new ArrayList<>();
        List<VacationHomeReservationResponse> vacationHomeReservationResponses = new ArrayList<>();
        List<AdventureReservationResponse> adventureReservationResponses = new ArrayList<>();

        UserHistoryResponse userHistoryResponse = new UserHistoryResponse();
        List<BoatReservation> boatReservations = boatReservationRepository.findBoatReservationsByUserId(user.getId());
        List<VacationHomeReservation> vacationHomeReservations = vacationHomeReservationRepository.findVacationHomeReservationsByUserId(user.getId());
        List<AdventureReservation> adventureReservations = adventureReservationRepository.findAdventureReservationsByUserId(user.getId());

        Date today = new Date();


        for(BoatReservation boatReservation: boatReservations)
        {
            if(today.after(boatReservation.getEndDate()) && today.after(boatReservation.getStartDate()) && boatReservation.getStatus() != ReservationStatus.CANCELED) {
                boatReservation.setStatus(ReservationStatus.FINISHED);
                this.boatReservationRepository.save(boatReservation);
            } else if(boatReservation.getStartDate().before(today) && boatReservation.getEndDate().after(today) && boatReservation.getStatus() != ReservationStatus.CANCELED) {
                boatReservation.setStatus(ReservationStatus.IN_PROGRESS);
                this.boatReservationRepository.save(boatReservation);
            }
            BoatReservationResponse boatReservationResponse = new BoatReservationResponse(boatReservation);
            boatReservationResponses.add(boatReservationResponse);
        }

        for(VacationHomeReservation vacationHomeReservation: vacationHomeReservations) {
            if(today.after(vacationHomeReservation.getEndDate()) && today.after(vacationHomeReservation.getStartDate()) && vacationHomeReservation.getStatus() != ReservationStatus.CANCELED) {
                vacationHomeReservation.setStatus(ReservationStatus.FINISHED);
                this.vacationHomeReservationRepository.save(vacationHomeReservation);
            } else if(vacationHomeReservation.getStartDate().before(today) && vacationHomeReservation.getEndDate().after(today) && vacationHomeReservation.getStatus() != ReservationStatus.CANCELED) {
                vacationHomeReservation.setStatus(ReservationStatus.IN_PROGRESS);
                this.vacationHomeReservationRepository.save(vacationHomeReservation);
            }
            VacationHomeReservationResponse vacationHomeReservationResponse = new VacationHomeReservationResponse(vacationHomeReservation);
            vacationHomeReservationResponses.add(vacationHomeReservationResponse);
        }

        for(AdventureReservation adventureReservation: adventureReservations) {
            if(today.after(adventureReservation.getEndDate()) && today.after(adventureReservation.getStartDate()) && adventureReservation.getStatus() != ReservationStatus.CANCELED) {
                adventureReservation.setStatus(ReservationStatus.FINISHED);
                this.adventureReservationRepository.save(adventureReservation);
            } else if(adventureReservation.getStartDate().before(today) && adventureReservation.getEndDate().after(today) && adventureReservation.getStatus() != ReservationStatus.CANCELED) {
                adventureReservation.setStatus(ReservationStatus.IN_PROGRESS);
                this.adventureReservationRepository.save(adventureReservation);
            }
            AdventureReservationResponse adventureReservationResponse = new AdventureReservationResponse(adventureReservation);
            adventureReservationResponses.add(adventureReservationResponse);
        }
        userHistoryResponse.setBoatReservations(boatReservationResponses);
        userHistoryResponse.setAdventureReservations(adventureReservationResponses);
        userHistoryResponse.setVacationHomeReservations(vacationHomeReservationResponses);
        return userHistoryResponse;
    }

    @Override
    public AdventureReservation saveAdventureReservation(AdventureReservationRequest adventureReservationRequest) {
        AdventureReservation adventureReservation = null;

        return null;
    }

    @Override
    public UserHistoryResponse cancelBoatReservation(CancelReservationRequest cancelReservationRequest) {
        Optional<BoatReservation> boatReservation = this.boatReservationRepository.findById(cancelReservationRequest.getId());
        boatReservation.get().setStatus(ReservationStatus.CANCELED);
        this.boatReservationRepository.save(boatReservation.get());
        return  getAllReservations(cancelReservationRequest.getEmail());
    }

    @Override
    public UserHistoryResponse cancelAdventureReservation(CancelReservationRequest cancelReservationRequest) {
        Optional<AdventureReservation> adventureReservation = this.adventureReservationRepository.findById(cancelReservationRequest.getId());
        adventureReservation.get().setStatus(ReservationStatus.CANCELED);
        this.adventureReservationRepository.save(adventureReservation.get());
        return  getAllReservations(cancelReservationRequest.getEmail());
    }

    @Override
    public UserHistoryResponse cancelVacationHomeReservation(CancelReservationRequest cancelReservationRequest) {
        Optional<VacationHomeReservation> vacationHomeReservation = this.vacationHomeReservationRepository.findById(cancelReservationRequest.getId());
        vacationHomeReservation.get().setStatus(ReservationStatus.CANCELED);
        this.vacationHomeReservationRepository.save(vacationHomeReservation.get());
        return  getAllReservations(cancelReservationRequest.getEmail());
    }
}
