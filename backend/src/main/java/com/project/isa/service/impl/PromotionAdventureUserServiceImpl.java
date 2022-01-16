package com.project.isa.service.impl;

import com.project.isa.model.Adventure;
import com.project.isa.model.PromotionAdventureUser;
import com.project.isa.model.PromotionAdventure;
import com.project.isa.model.User;
import com.project.isa.repository.PromotionAdventureUserRepository;
import com.project.isa.request.PromotionAdventureUserRequest;
import com.project.isa.service.PromotionAdventureUserService;
import com.project.isa.service.AdventureService;
import com.project.isa.service.PromotionAdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionAdventureUserServiceImpl implements PromotionAdventureUserService {

    @Autowired
    PromotionAdventureUserRepository promotionAdventureUserRepository;

    @Autowired
    PromotionAdventureService promotionAdventureService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    AdventureService adventureService;

    @Override
    public PromotionAdventureUser findById(Long id) {
        return promotionAdventureUserRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<PromotionAdventureUser> findAll() {
        return promotionAdventureUserRepository.findAll();
    }

    @Override
    public List<Adventure> findAllSubscribed() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());
        List<PromotionAdventureUser> promotionAdventureUserList = findAll();
        List<Adventure> temp = new ArrayList<>();
        for(PromotionAdventureUser apu: promotionAdventureUserList){
            if(apu.getPromotionUser().getId().equals(user.getId()) && apu.isSubscribed() == true){
                temp.add(adventureService.findById(apu.getAdventure().getId()));
            }
        }
        return temp;
    }

    @Override
    public PromotionAdventureUser subscribeToPromotions(PromotionAdventureUserRequest promotionAdventureUserRequest) {
        PromotionAdventureUser promotionAdventureUser = new PromotionAdventureUser();

        Adventure adventure = adventureService.findById(promotionAdventureUserRequest.getAdventureId());
        promotionAdventureUser.setAdventure(adventure);

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<PromotionAdventureUser> promotionAdventureUserList = findAll();
        for(PromotionAdventureUser apu: promotionAdventureUserList){
            if(apu.getAdventure().getId().equals(promotionAdventureUser.getAdventure().getId()) && apu.getPromotionUser().getId().equals(user.getId())) {
                return null;
            }
        }
        promotionAdventureUser.setPromotionUser(user);
        promotionAdventureUser.setSubscribed(true);

        return promotionAdventureUserRepository.save(promotionAdventureUser);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(PromotionAdventureUser promotionAdventureUser) {
        promotionAdventureUserRepository.delete(promotionAdventureUser);
    }

    public PromotionAdventureUser findByAdventureId(Long adventureId) {
        List<PromotionAdventureUser> list = findAll();
        PromotionAdventureUser temp = new PromotionAdventureUser();

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        Adventure adventure = adventureService.findById(adventureId);

        for (PromotionAdventureUser pau: list){
            if(pau.getAdventure().getId().equals(adventure.getId()) && pau.getPromotionUser().getId().equals(user.getId())){
                temp = pau;
            }
        }
        return temp;
    }

    public PromotionAdventureUser unsubscribe(Long adventureId){
        PromotionAdventureUser promotionAdventureUser = findByAdventureId(adventureId);
        promotionAdventureUser.setSubscribed(false);
        return promotionAdventureUserRepository.save(promotionAdventureUser);


    }



}
