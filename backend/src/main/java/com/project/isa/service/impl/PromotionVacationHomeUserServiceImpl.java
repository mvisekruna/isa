package com.project.isa.service.impl;

import com.project.isa.model.*;
import com.project.isa.repository.PromotionVacationHomeRepository;
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

    @Autowired
    PromotionVacationHomeRepository promotionVacationHomeRepository;

    @Override
    public PromotionVacationHomeUser findById(Long id) {
        return promotionVacationHomeUserRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<PromotionVacationHomeUser> findAll() {
        return promotionVacationHomeUserRepository.findAll();
    }

    @Override
    public List<PromotionVacationHome> findAllWithVacationHomeId(Long vacationHomeId) {
        List<PromotionVacationHomeUser> list = findAll();
        List<PromotionVacationHome> temp = new ArrayList<>();

        for(PromotionVacationHomeUser pvhu: list) {
            if(pvhu.getVacationHome().getId().equals(vacationHomeId)){
                temp = promotionVacationHomeService.findAllVacationHomePromotions();
            }
        }
        return temp;
    }

    @Override
    public List<PromotionVacationHomeUser> findAllWithPromotionId(Long promotionId) {
        List<PromotionVacationHomeUser> promotionVacationHomeUserList = findAll();
        List<PromotionVacationHomeUser> temp = new ArrayList<>();

        for(PromotionVacationHomeUser pvhu: promotionVacationHomeUserList){
            if(pvhu.getPromotionVacationHome().getId().equals(promotionId)){
                temp.add(pvhu);
            }
        }
        return temp;
    }

    @Override
    public PromotionVacationHomeUser chooseThePromotion(Long promotionId) {
        PromotionVacationHomeUser promotionVacationHomeUser = new PromotionVacationHomeUser();
        PromotionVacationHome promotionVacationHome = promotionVacationHomeService.findById(promotionId);
        // TODO: dodati uslov da li upada u vec izabrane rezervacije, tj period, za tog korisnika
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<PromotionVacationHomeUser> promotionVacationHomeUserList = findAll();
        for(PromotionVacationHomeUser pvhu: promotionVacationHomeUserList){
            if(pvhu.getPromotionUser().getId().equals(user.getId())) {
                if(promotionId.equals(pvhu.getPromotionVacationHome().getId())) {
                    return null;
                }
            }
        }

        VacationHome vacationHome = vacationHomeService.findById(promotionVacationHome.getVacationHomePromotion().getId());
        promotionVacationHomeUser.setVacationHome(vacationHome);
        promotionVacationHomeUser.setPromotionVacationHome(promotionVacationHome);
        promotionVacationHomeUser.setPromotionUser(user);
        promotionVacationHomeUser.setSubscribed(true);
        promotionVacationHome.setNumberOfPromotions(promotionVacationHome.getNumberOfPromotions() - 1);
        promotionVacationHomeRepository.save(promotionVacationHome);

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

        for (PromotionVacationHomeUser pvhu: list){
            if(pvhu.getVacationHome().getId().equals(vacationHome.getId()) && pvhu.getPromotionUser().getId().equals(user.getId())){
                temp = pvhu;
            }
        }
        return temp;
    }

    @Override
    public PromotionVacationHome cancelThePromotion(Long id) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<PromotionVacationHomeUser> promotionVacationHomeUserList = findAll();
        PromotionVacationHome promotionVacationHome = promotionVacationHomeService.findById(id);
        for(PromotionVacationHomeUser pvhu: promotionVacationHomeUserList) {
            if(pvhu.getPromotionVacationHome().getId().equals(id)){
                promotionVacationHomeUserRepository.delete(pvhu);
            }
        }

        promotionVacationHome.setNumberOfPromotions(promotionVacationHome.getNumberOfPromotions() + 1);
        return promotionVacationHomeRepository.save(promotionVacationHome);
    }

    @Override
    public List<PromotionVacationHome> findAllPromotionsIChose() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<PromotionVacationHomeUser> promotionVacationHomeUserList = findAll();
        List<PromotionVacationHome> promotionVacationHomeList = new ArrayList<>();
        for(PromotionVacationHomeUser pvhu: promotionVacationHomeUserList){
            if(pvhu.getPromotionUser().getId().equals(user.getId())){
                promotionVacationHomeList.add(promotionVacationHomeService.findById(pvhu.getPromotionVacationHome().getId()));
            }
        }

        return promotionVacationHomeList;
    }

}