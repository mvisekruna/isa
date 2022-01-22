package com.project.isa.service.impl;

import com.project.isa.model.Adventure;
import com.project.isa.model.PromotionAdventureUser;
import com.project.isa.model.PromotionAdventure;
import com.project.isa.model.User;
import com.project.isa.repository.PromotionAdventureRepository;
import com.project.isa.repository.PromotionAdventureUserRepository;
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

    @Autowired
    PromotionAdventureRepository promotionAdventureRepository;

    @Override
    public PromotionAdventureUser findById(Long id) {
        return promotionAdventureUserRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<PromotionAdventureUser> findAll() {
        return promotionAdventureUserRepository.findAll();
    }

    @Override
    public List<PromotionAdventure> findAllWithAdventureId(Long adventureId) {
        List<PromotionAdventureUser> list = findAll();
        List<PromotionAdventure> temp = new ArrayList<>();

        for(PromotionAdventureUser pau: list) {
            if(pau.getAdventure().getId().equals(adventureId)){
                temp = promotionAdventureService.findAllAdventurePromotions();
            }
        }
        return temp;
    }

    @Override
    public List<PromotionAdventureUser> findAllWithPromotionId(Long promotionId) {
        List<PromotionAdventureUser> promotionAdventureUserList = findAll();
        List<PromotionAdventureUser> temp = new ArrayList<>();

        for(PromotionAdventureUser pau: promotionAdventureUserList){
            if(pau.getPromotionAdventure().getId().equals(promotionId)){
                temp.add(pau);
            }
        }
        return temp;
    }

    @Override
    public PromotionAdventureUser chooseThePromotion(Long promotionId) { //choose the promotion from the promotion list
        PromotionAdventureUser promotionAdventureUser = new PromotionAdventureUser();
        PromotionAdventure promotionAdventure = promotionAdventureService.findById(promotionId);
    // TODO: dodati uslov da li upada u vec izabrane rezervacije, tj period, za tog korisnika
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<PromotionAdventureUser> promotionAdventureUserList = findAll();
        for(PromotionAdventureUser apu: promotionAdventureUserList){
            if(apu.getPromotionUser().getId().equals(user.getId())) {
                if(promotionId.equals(apu.getPromotionAdventure().getId())) {
                    return null;
                }
            }
        }

        Adventure adventure = adventureService.findById(promotionAdventure.getAdventurePromotion().getId());
        promotionAdventureUser.setAdventure(adventure);
        promotionAdventureUser.setPromotionAdventure(promotionAdventure);
        promotionAdventureUser.setPromotionUser(user);
        promotionAdventureUser.setSubscribed(true);
        promotionAdventure.setNumberOfPromotions(promotionAdventure.getNumberOfPromotions() - 1);
        promotionAdventureRepository.save(promotionAdventure);

        return promotionAdventureUserRepository.save(promotionAdventureUser);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(PromotionAdventureUser promotionAdventureUser) {
        promotionAdventureUserRepository.delete(promotionAdventureUser);
    }

    @Override
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

    @Override
    public PromotionAdventure cancelThePromotion(Long id){
        //TODO: UBACITI USLOV ZA TRI DANA PRE
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<PromotionAdventureUser> promotionAdventureUserList = findAll();
        PromotionAdventure promotionAdventure = promotionAdventureService.findById(id);
        for(PromotionAdventureUser pau: promotionAdventureUserList) {
            if(pau.getPromotionAdventure().getId().equals(id)){
                promotionAdventureUserRepository.delete(pau);
            }
        }

        promotionAdventure.setNumberOfPromotions(promotionAdventure.getNumberOfPromotions() + 1);
        return promotionAdventureRepository.save(promotionAdventure);
    }

    @Override
    public List<PromotionAdventure> findAllPromotionsIChose() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<PromotionAdventureUser> promotionAdventureUserList = findAll();
        List<PromotionAdventure> promotionAdventureList = new ArrayList<>();
        for(PromotionAdventureUser pau: promotionAdventureUserList){
            if(pau.getPromotionUser().getId().equals(user.getId())){
                promotionAdventureList.add(promotionAdventureService.findById(pau.getPromotionAdventure().getId()));
            }
        }

        return promotionAdventureList;
    }
}