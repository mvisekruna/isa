package com.project.isa.service.impl;

import com.project.isa.enumeration.ReservationStatus;
import com.project.isa.model.*;
import com.project.isa.repository.*;
import com.project.isa.request.AdventureReservationRequest;
import com.project.isa.request.CancelReservationRequest;
import com.project.isa.response.AdventureReservationResponse;
import com.project.isa.response.BoatReservationResponse;
import com.project.isa.response.UserHistoryResponse;
import com.project.isa.response.VacationHomeReservationResponse;
import com.project.isa.service.AdventureService;
import com.project.isa.service.PromotionAdventureService;
import com.project.isa.service.PromotionAdventureUserService;
import com.project.isa.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private PromotionAdventureUserService promotionAdventureUserService;

    @Autowired
    private PromotionAdventureService promotionAdventureService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public List<AdventureReservation> findAll() {
        return adventureReservationRepository.findAll();
    }

    public AdventureReservation findById(Long id) {
        return adventureReservationRepository.findById(id).orElseGet(null);
    }

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
    public List<AdventureReservation> getMyReservations() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<AdventureReservation> adventureReservationList = findAll();
        List<AdventureReservation> temp = new ArrayList<>();
        for(AdventureReservation ar: adventureReservationList){
            if(ar.getUser().getId().equals(user.getId()) && ar.getStatus().equals(ReservationStatus.NEW)){
                temp.add(ar);
            }
        }

        return temp;
    }

    @Override
    public List<Adventure> getFreeAdventures(AdventureReservationRequest adventureReservationRequest) {
        List<Adventure> adventureList = new ArrayList<>();
        Adventure temp = new Adventure();
        Date start = Timestamp.valueOf(adventureReservationRequest.getStartDate());
        Date end = Timestamp.valueOf(adventureReservationRequest.getEndDate());

//        List<PromotionAdventureUser> promotionAdventureUserList = promotionAdventureUserService.findAll();
//        List<PromotionAdventure> promotionAdventureList = promotionAdventureUserService.findAllWithAdventureId(adventureReservationRequest.getAdventureId());
//        for(PromotionAdventureUser pau: promotionAdventureUserList) {
//            if(pau.getAdventure().equals(adventureReservationRequest.getAdventureId())){
//                for(PromotionAdventure pa: promotionAdventureList) {
//                    if(!(pa.getEndPromo().before(start) || pa.getStartPromo().after(end))) {
//                        return null;
//                    }
//                }
//            }
//        }

        List<AdventureReservation> adventureReservationList = adventureReservationRepository.findAll();
        for(AdventureReservation ar: adventureReservationList){
            if(ar.getAdventure().getId().equals(adventureReservationRequest.getAdventureId())
                    || ar.getStatus().equals("FINISHED") || ar.getStatus().equals("CANCELED")){
                if(ar.getEndDate().before(start) || ar.getStartDate().after(end)) {
                    temp = adventureService.findById(ar.getAdventure().getId());
                    if(adventureList.isEmpty()){
                        adventureList.add(temp);
                    } else {
                        for(Adventure a: adventureList){
                            if(!(a.getId().equals(temp.getId()))){
                                adventureList.add(temp);
                            }
                        }
                    }
                }
            }
        }

        return adventureList;
    }

    @Override
    public AdventureReservation chooseAdventure(AdventureReservationRequest adventureReservationRequest) {
        AdventureReservation adventureReservation = new AdventureReservation();

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        Date start = Timestamp.valueOf(adventureReservationRequest.getStartDate());
        Date end = Timestamp.valueOf(adventureReservationRequest.getEndDate());

        Adventure adventure = adventureService.findById(adventureReservationRequest.getAdventureId());
        adventureReservation.setAdventure(adventure);
        adventureReservation.setStartDate(start);
        adventureReservation.setEndDate(end);
        adventureReservation.setUser(user);
        adventureReservation.setStatus(ReservationStatus.NEW);

        return adventureReservationRepository.save(adventureReservation);
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
