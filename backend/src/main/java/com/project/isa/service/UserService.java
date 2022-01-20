package com.project.isa.service;

import com.project.isa.model.Adventure;
import com.project.isa.model.User;
import com.project.isa.request.UserRequest;
import com.project.isa.request.UserUpdateRequest;

import java.util.List;

public interface UserService {
    User findById(Long id);
    User findByEmail(String email);
    List<User> findAll();
    User register(UserRequest userRequest);
    User registerAdmin(UserRequest userRequest);
    User update(UserUpdateRequest userUpdateRequest);
    User registrationForOthers(UserRequest userRequest);
    User subscribeToPromotions(Long adventureId);
    void cancelMySubscription(Long adventureId);
    List<Adventure> findMySubscribed();

    List<User> findAllTutors();

}
