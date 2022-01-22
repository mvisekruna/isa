package com.project.isa.service;

import com.project.isa.model.Adventure;
import com.project.isa.model.Boat;
import com.project.isa.model.User;
import com.project.isa.model.VacationHome;
import com.project.isa.request.UserRequest;
import com.project.isa.request.UserUpdateRequest;

import java.util.List;

public interface UserService {
    User findById(Long id);
    User findByEmail(String email);
    List<User> findAll();
    User register(UserRequest userRequest);
    User registerAdmin(UserRequest userRequest);
    User registrationForOthers(UserRequest userRequest);
    User update(UserUpdateRequest userUpdateRequest);
    List<User> findAllTutors();
    User activateAccount(String email);
    User deactivateAccount(String email);

    /**ADVENTURE*******/
    User subscribeToAdventurePromotions(Long adventureId);
    void cancelMyAdventureSubscription(Long adventureId);
    List<Adventure> findMySubscribedAdventures();

    /**BOAT*******/
    User subscribeToBoatPromotions(Long boatId);
    void cancelMyBoatSubscription(Long boatId);
    List<Boat> findMySubscribedBoats();

    /**VACATION HOME*******/
    User subscribeToVacationHomePromotions(Long vacationHomeId);
    void cancelMyVacationHomeSubscription(Long vacationHomeId);
    List<VacationHome> findMySubscribedVacationHomes();
}
