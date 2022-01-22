package com.project.isa.service.impl;

import com.project.isa.model.*;
import com.project.isa.repository.PromotionBoatRepository;
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

    @Autowired
    PromotionBoatRepository promotionBoatRepository;

    @Override
    public PromotionBoatUser findById(Long id) {
        return promotionBoatUserRepository.findById(id).orElseGet(null);    }

    @Override
    public List<PromotionBoatUser> findAll() {
        return promotionBoatUserRepository.findAll();
    }

    @Override
    public List<PromotionBoat> findAllWithBoatId(Long boatId) {
        List<PromotionBoatUser> list = findAll();
        List<PromotionBoat> temp = new ArrayList<>();

        for(PromotionBoatUser pbu: list) {
            if(pbu.getBoat().getId().equals(boatId)){
                temp = promotionBoatService.findAllBoatPromotions();
            }
        }
        return temp;
    }

    @Override
    public List<PromotionBoatUser> findAllWithPromotionId(Long promotionId) {
        List<PromotionBoatUser> promotionBoatUserList = findAll();
        List<PromotionBoatUser> temp = new ArrayList<>();

        for(PromotionBoatUser pbu: promotionBoatUserList){
            if(pbu.getPromotionBoat().getId().equals(promotionId)){
                temp.add(pbu);
            }
        }
        return temp;
    }

    @Override
    public PromotionBoatUser chooseThePromotion(Long promotionId) {
        PromotionBoatUser promotionBoatUser = new PromotionBoatUser();
        PromotionBoat promotionBoat = promotionBoatService.findById(promotionId);
        // TODO: dodati uslov da li upada u vec izabrane rezervacije, tj period, za tog korisnika
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<PromotionBoatUser> promotionBoatUserList = findAll();
        for(PromotionBoatUser pbu: promotionBoatUserList){
            if(pbu.getPromotionUser().getId().equals(user.getId())) {
                if(promotionId.equals(pbu.getPromotionBoat().getId())) {
                    return null;
                }
            }
        }

        Boat boat = boatService.findById(promotionBoat.getBoatPromotion().getId());
        promotionBoatUser.setBoat(boat);
        promotionBoatUser.setPromotionBoat(promotionBoat);
        promotionBoatUser.setPromotionUser(user);
        promotionBoatUser.setSubscribed(true);
        promotionBoat.setNumberOfPromotions(promotionBoat.getNumberOfPromotions() - 1);
        promotionBoatRepository.save(promotionBoat);

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

        for (PromotionBoatUser pbu: list){
            if(pbu.getBoat().getId().equals(boat.getId()) && pbu.getPromotionUser().getId().equals(user.getId())){
                temp = pbu;
            }
        }
        return temp;
    }

    @Override
    public PromotionBoat cancelThePromotion(Long id) {
        //TODO: UBACITI USLOV ZA TRI DANA PRE
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<PromotionBoatUser> promotionBoatUserList = findAll();
        PromotionBoat promotionBoat = promotionBoatService.findById(id);
        for(PromotionBoatUser pbu: promotionBoatUserList) {
            if(pbu.getPromotionBoat().getId().equals(id)){
                promotionBoatUserRepository.delete(pbu);
            }
        }

        promotionBoat.setNumberOfPromotions(promotionBoat.getNumberOfPromotions() + 1);
        return promotionBoatRepository.save(promotionBoat);
    }

    @Override
    public List<PromotionBoat> findAllPromotionsIChose() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<PromotionBoatUser> promotionBoatUserList = findAll();
        List<PromotionBoat> promotionBoatList = new ArrayList<>();
        for(PromotionBoatUser pbu: promotionBoatUserList){
            if(pbu.getPromotionUser().getId().equals(user.getId())){
                promotionBoatList.add(promotionBoatService.findById(pbu.getPromotionBoat().getId()));
            }
        }

        return promotionBoatList;
    }

}
