package com.project.isa.service.impl;

import com.project.isa.enumeration.ReservationStatus;
import com.project.isa.model.*;
import com.project.isa.repository.*;
import com.project.isa.request.AdventureReservationRequest;
import com.project.isa.request.BoatReservationRequest;
import com.project.isa.request.CancelReservationRequest;
import com.project.isa.request.VacationHomeReservationRequest;
import com.project.isa.response.AdventureReservationResponse;
import com.project.isa.response.BoatReservationResponse;
import com.project.isa.response.UserHistoryResponse;
import com.project.isa.response.VacationHomeReservationResponse;
import com.project.isa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private BoatService boatService;

    @Autowired
    private PromotionAdventureUserService promotionAdventureUserService;

    @Autowired
    private PromotionAdventureService promotionAdventureService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private VacationHomeService vacationHomeService;

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

    /**ADVENTURE RESERVATIONS****************/
    @Override
    public List<AdventureReservation> findAllAdventureReservations() {
        return adventureReservationRepository.findAll();
    }
    @Override
    public AdventureReservation findByIdAdventureReservation(Long id) {
        return adventureReservationRepository.findById(id).orElseGet(null);
    }
    @Override
    public List<AdventureReservation> getMyAdventureReservations() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<AdventureReservation> adventureReservationList = findAllAdventureReservations();
        List<AdventureReservation> temp = new ArrayList<>();
        for(AdventureReservation ar: adventureReservationList){
            if(ar.getUser().getId().equals(user.getId()) && ar.getStatus().equals(ReservationStatus.NEW)){
                temp.add(ar);
            }
        }

        return temp;
    }
    @Override
    public List<Adventure> getFreeAdventures(AdventureReservationRequest adventureReservationRequest) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    // TODO: PROMENITI DA PRIMA IME AVANTURE ne ID
        List<Adventure> adventureList = new ArrayList<>();
        Adventure temp = new Adventure();
        Date start = formatter.parse(adventureReservationRequest.getStartDate());
        Date end = formatter.parse(adventureReservationRequest.getEndDate());

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
                    && (ar.getStatus().equals(ReservationStatus.FINISHED) || ar.getStatus().equals(ReservationStatus.CANCELED))){
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
    public AdventureReservation chooseAdventure(AdventureReservationRequest adventureReservationRequest) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        AdventureReservation adventureReservation = new AdventureReservation();

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        Date start = formatter.parse(adventureReservationRequest.getStartDate());
        Date end = formatter.parse(adventureReservationRequest.getEndDate());

        Adventure adventure = adventureService.findById(adventureReservationRequest.getAdventureId());
        adventureReservation.setAdventure(adventure);
        adventureReservation.setStartDate(start);
        adventureReservation.setEndDate(end);
        adventureReservation.setUser(user);
        adventureReservation.setStatus(ReservationStatus.NEW);

        return adventureReservationRepository.save(adventureReservation);
    }
    @Override
    public UserHistoryResponse cancelAdventureReservation(CancelReservationRequest cancelReservationRequest) {
        Optional<AdventureReservation> adventureReservation = this.adventureReservationRepository.findById(cancelReservationRequest.getId());
        adventureReservation.get().setStatus(ReservationStatus.CANCELED);
        this.adventureReservationRepository.save(adventureReservation.get());
        return  getAllReservations(cancelReservationRequest.getEmail());
    }

    /**BOAT RESERVATIONS****************/
    @Override
    public List<BoatReservation> findAllBoatReservations() {
        return boatReservationRepository.findAll();
    }
    @Override
    public BoatReservation findByIdBoatReservation(Long id) {
        return boatReservationRepository.findById(id).orElseGet(null);
    }
    @Override
    public List<BoatReservation> getMyBoatReservations() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<BoatReservation> boatReservationList = findAllBoatReservations();
        List<BoatReservation> temp = new ArrayList<>();
        for(BoatReservation br: boatReservationList) {
            if(br.getUser().getId().equals(user.getId()) && br.getStatus().equals(ReservationStatus.NEW)) {
                temp.add(br);
            }
        }
        return temp;
    }
    @Override
    public List<Boat> getFreeBoats(BoatReservationRequest boatReservationRequest) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        List<Boat> boatList = new ArrayList<>();
        Boat temp = new Boat();
        Date start = formatter.parse(boatReservationRequest.getStartDate());
        Date end = formatter.parse(boatReservationRequest.getEndDate());

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

        List<BoatReservation> boatReservationList = boatReservationRepository.findAll();
        for(BoatReservation br: boatReservationList){
            if(br.getBoat().getId().equals(boatReservationRequest.getBoatId())
                    || br.getStatus().equals(ReservationStatus.FINISHED) || br.getStatus().equals(ReservationStatus.CANCELED)){
                if(br.getEndDate().before(start) || br.getStartDate().after(end)) {
                    temp = boatService.findById(br.getBoat().getId());
                    if(boatList.isEmpty()){
                        boatList.add(temp);
                    } else {
                        for(Boat b: boatList){
                            if(!(b.getId().equals(temp.getId()))){
                                boatList.add(temp);
                            }
                        }
                    }
                }
            }
        }

        return boatList;
    }
    @Override
    public BoatReservation chooseBoat(BoatReservationRequest boatReservationRequest) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        BoatReservation boatReservation = new BoatReservation();

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        Date start = formatter.parse(boatReservationRequest.getStartDate());
        Date end = formatter.parse(boatReservationRequest.getEndDate());

        Boat boat = boatService.findById(boatReservationRequest.getBoatId());
        boatReservation.setBoat(boat);
        boatReservation.setStartDate(start);
        boatReservation.setEndDate(end);
        boatReservation.setUser(user);
        boatReservation.setStatus(ReservationStatus.NEW);

        return boatReservationRepository.save(boatReservation);
    }
    @Override
    public UserHistoryResponse cancelBoatReservation(CancelReservationRequest cancelReservationRequest) {
        Optional<BoatReservation> boatReservation = this.boatReservationRepository.findById(cancelReservationRequest.getId());
        boatReservation.get().setStatus(ReservationStatus.CANCELED);
        this.boatReservationRepository.save(boatReservation.get());
        return  getAllReservations(cancelReservationRequest.getEmail());
    }

    /**VACATION HOME RESERVATIONS****************/
    @Override
    public List<VacationHomeReservation> findAllVacationHomeReservations() {
        return vacationHomeReservationRepository.findAll();
    }

    @Override
    public VacationHomeReservation findByIdVacationHomeReservation(Long id) {
        return vacationHomeReservationRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<VacationHomeReservation> getMyVacationHomeReservations() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<VacationHomeReservation> vacationHomeReservationList = findAllVacationHomeReservations();
        List<VacationHomeReservation> temp = new ArrayList<>();
        for(VacationHomeReservation vhr: vacationHomeReservationList){
            if(vhr.getUser().getId().equals(user.getId()) && vhr.getStatus().equals(ReservationStatus.NEW)){
                temp.add(vhr);
            }
        }

        return temp;
    }

    @Override
    public List<VacationHome> getFreeVacationHomes(VacationHomeReservationRequest vacationHomeReservationRequest) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        // TODO: PROMENITI DA PRIMA IME AVANTURE ne ID
        List<VacationHome> vacationHomeList = new ArrayList<>();
        VacationHome temp = new VacationHome();
        Date start = formatter.parse(vacationHomeReservationRequest.getStartDate());
        Date end = formatter.parse(vacationHomeReservationRequest.getEndDate());

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

        List<VacationHomeReservation> vacationHomeReservationList = vacationHomeReservationRepository.findAll();
        for(VacationHomeReservation vhr: vacationHomeReservationList){
            if(vhr.getVacationHome().getId().equals(vacationHomeReservationRequest.getVacationHomeId())
                    && (vhr.getStatus().equals(ReservationStatus.FINISHED) || vhr.getStatus().equals(ReservationStatus.CANCELED))){
                if(vhr.getEndDate().before(start) || vhr.getStartDate().after(end)) {
                    temp = vacationHomeService.findById(vhr.getVacationHome().getId());
                    if(vacationHomeList.isEmpty()){
                        vacationHomeList.add(temp);
                    } else {
                        for(VacationHome vh: vacationHomeList){
                            if(!(vh.getId().equals(temp.getId()))){
                                vacationHomeList.add(temp);
                            }
                        }
                    }
                }
            }
        }

        return vacationHomeList;
    }

    @Override
    public VacationHomeReservation chooseVacationHome(VacationHomeReservationRequest vacationHomeReservationRequest) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        VacationHomeReservation vacationHomeReservation = new VacationHomeReservation();

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        Date start = formatter.parse(vacationHomeReservationRequest.getStartDate());
        Date end = formatter.parse(vacationHomeReservationRequest.getEndDate());

        VacationHome vacationHome = vacationHomeService.findById(vacationHomeReservationRequest.getVacationHomeId());
        vacationHomeReservation.setVacationHome(vacationHome);
        vacationHomeReservation.setStartDate(start);
        vacationHomeReservation.setEndDate(end);
        vacationHomeReservation.setUser(user);
        vacationHomeReservation.setStatus(ReservationStatus.NEW);

        return vacationHomeReservationRepository.save(vacationHomeReservation);
    }

    @Override
    public UserHistoryResponse cancelVacationHomeReservation(CancelReservationRequest cancelReservationRequest) {
        Optional<VacationHomeReservation> vacationHomeReservation = this.vacationHomeReservationRepository.findById(cancelReservationRequest.getId());
        vacationHomeReservation.get().setStatus(ReservationStatus.CANCELED);
        this.vacationHomeReservationRepository.save(vacationHomeReservation.get());
        return  getAllReservations(cancelReservationRequest.getEmail());
    }
}
