package com.project.isa.service.impl;

import com.project.isa.model.*;
import com.project.isa.repository.PromotionBoatUserRepository;
import com.project.isa.request.PromotionBoatUserRequest;
import com.project.isa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionBoatUserServiceImpl implements PromotionBoatUserService {

    @Autowired
    PromotionBoatUserRepository promotionBoatUserRepository;

    @Autowired
    PromotionBoatService promotionBoatService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    BoatService boatService;

    @Override
    public PromotionBoatUser findById(Long id) {
        return promotionBoatUserRepository.findById(id).orElseGet(null);    }

    @Override
    public List<PromotionBoatUser> findAll() {
        return promotionBoatUserRepository.findAll();
    }

    @Override
    public List<Boat> findAllSubscribed() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());
        List<PromotionBoatUser> promotionBoatUsers = findAll();
        List<Boat> temp = new ArrayList<>();
        for(PromotionBoatUser apu: promotionBoatUsers) {
            if(apu.getPromotionUser().getId().equals(user.getId()) && apu.isSubscribed() == true){
                temp.add(boatService.findById(apu.getBoat().getId()));
            }
        }
        return temp;
    }

    @Override
    public PromotionBoatUser subscribeToPromotions(Long boatId) {
        PromotionBoatUser promotionBoatUser = new PromotionBoatUser();

        Boat boat = boatService.findById(boatId);
        promotionBoatUser.setBoat(boat);

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<PromotionBoatUser> promotionBoatUserList = findAll();
        for(PromotionBoatUser apu: promotionBoatUserList){
            if(apu.getBoat().getId().equals(promotionBoatUser.getBoat().getId()) && apu.getPromotionUser().getId().equals(user.getId())) {
                return null;
            }
        }
        promotionBoatUser.setPromotionUser(user);
        promotionBoatUser.setSubscribed(true);

        return promotionBoatUserRepository.save(promotionBoatUser);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(PromotionBoatUser promotionBoatUser) {
        promotionBoatUserRepository.delete(promotionBoatUser);
    }

    public PromotionBoatUser findByBoatId(Long boatId) {
        List<PromotionBoatUser> list = findAll();
        PromotionBoatUser temp = new PromotionBoatUser();

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        Boat boat = boatService.findById(boatId);

        for (PromotionBoatUser pau: list){
            if(pau.getBoat().getId().equals(boat.getId()) && pau.getPromotionUser().getId().equals(user.getId())){
                temp = pau;
            }
        }
        return temp;
    }

    public PromotionBoatUser unsubscribe(Long boatId){
        PromotionBoatUser promotionBoatUser = findByBoatId(boatId);
        promotionBoatUser.setSubscribed(false);
        return promotionBoatUserRepository.save(promotionBoatUser);

    }
}
