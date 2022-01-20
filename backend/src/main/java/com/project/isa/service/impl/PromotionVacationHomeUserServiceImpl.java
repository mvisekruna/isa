package com.project.isa.service.impl;

import com.project.isa.model.*;
import com.project.isa.repository.PromotionVacationHomeUserRepository;
import com.project.isa.service.PromotionVacationHomeService;
import com.project.isa.service.PromotionVacationHomeUserService;
import com.project.isa.service.VacationHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionVacationHomeUserServiceImpl implements PromotionVacationHomeUserService {

    @Autowired
    PromotionVacationHomeUserRepository promotionVacationHomeUserRepository;

    @Autowired
    PromotionVacationHomeService promotionVacationHomeService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    VacationHomeService vacationHomeService;


    @Override
    public PromotionVacationHomeUser findById(Long id) {
        return promotionVacationHomeUserRepository.findById(id).orElseGet(null);
    }


    @Override
    public List<PromotionVacationHomeUser> findAll() {
        return promotionVacationHomeUserRepository.findAll();
    }

    @Override
    public List<VacationHome> findAllSubscribed() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());
        List<PromotionVacationHomeUser> promotionVacationHomeUsers = findAll();
        List<VacationHome> temp = new ArrayList<>();
        for(PromotionVacationHomeUser apu: promotionVacationHomeUsers) {
            if(apu.getPromotionUser().getId().equals(user.getId()) && apu.isSubscribed() == true){
                temp.add(vacationHomeService.findById(apu.getVacationHome().getId()));
            }
        }
        return temp;    }

    @Override
    public PromotionVacationHomeUser subscribeToPromotions(Long vacationHomeId) {
        PromotionVacationHomeUser promotionVacationHomeUser = new PromotionVacationHomeUser();

        VacationHome vacationHome= vacationHomeService.findById(vacationHomeId);
        promotionVacationHomeUser.setVacationHome(vacationHome);

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<PromotionVacationHomeUser> promotionVacationHomeUserList = findAll();
        for(PromotionVacationHomeUser apu: promotionVacationHomeUserList){
            if(apu.getVacationHome().getId().equals(promotionVacationHomeUser.getVacationHome().getId()) && apu.getPromotionUser().getId().equals(user.getId())) {
                return null;
            }
        }
        promotionVacationHomeUser.setPromotionUser(user);
        promotionVacationHomeUser.setSubscribed(true);

        return promotionVacationHomeUserRepository.save(promotionVacationHomeUser);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(PromotionVacationHomeUser promotionVacationHomeUser) {
        promotionVacationHomeUserRepository.delete(promotionVacationHomeUser);
    }

    public PromotionVacationHomeUser findByVacationHomeId(Long vacationHomeId) {
        List<PromotionVacationHomeUser> list = findAll();
        PromotionVacationHomeUser temp = new PromotionVacationHomeUser();

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        VacationHome vacationHome = vacationHomeService.findById(vacationHomeId);

        for (PromotionVacationHomeUser pau: list){
            if(pau.getVacationHome().getId().equals(vacationHome.getId()) && pau.getPromotionUser().getId().equals(user.getId())){
                temp = pau;
            }
        }
        return temp;
    }

    public PromotionVacationHomeUser unsubscribe(Long vacationHomeId){
        PromotionVacationHomeUser promotionVacationHomeUser = findByVacationHomeId(vacationHomeId);
        promotionVacationHomeUser.setSubscribed(false);
        return promotionVacationHomeUserRepository.save(promotionVacationHomeUser);

    }
}
