package com.project.isa.service.impl;

import com.project.isa.model.Adventure;
import com.project.isa.model.AdventurePromotionUser;
import com.project.isa.model.Promotion;
import com.project.isa.model.User;
import com.project.isa.repository.PromotionRepository;
import com.project.isa.request.PromotionRequest;
import com.project.isa.service.AdventurePromotionUserService;
import com.project.isa.service.AdventureService;
import com.project.isa.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private AdventurePromotionUserService adventurePromotionUserService;

    @Autowired
    private EmailServiceImpl emailService;

    @Override
    public List<Promotion> findAllAdventurePromotions(){
        List<Promotion> promotions = promotionRepository.findAll();
        List<Promotion> adventurePromotions = new ArrayList<Promotion>();
        for(Promotion p: promotions){
            if(!p.getAdventurePromotion().getId().equals(null)){
                adventurePromotions.add(p);
            }
        }
    return adventurePromotions;
    }


    @Override
    public Promotion findById(Long id) throws AccessDeniedException {
        Promotion promotion = promotionRepository.findById(id).orElseGet(null);
        return promotion;
    }

    @Override
    public Promotion addPromotionToAdventure(PromotionRequest promotionRequest) {
        Adventure adventure = adventureService.findById((promotionRequest.getAdventurePromotionId()));
        Promotion promotion = new Promotion();

        promotion.setAdventurePromotion(adventure);
        promotion.setBoatPromotion(null);
        promotion.setVacationHomePromotion(null);
        promotion.setStartPromo(Timestamp.valueOf(promotionRequest.getStartPromo()));
        promotion.setEndPromo(Timestamp.valueOf(promotionRequest.getEndPromo()));
        promotion.setDescription(promotionRequest.getDescription());

        promotionRepository.save(promotion);

        List<AdventurePromotionUser> adventurePromotionUserList = adventurePromotionUserService.findAll();
        for(AdventurePromotionUser advenuturePromotionUser:adventurePromotionUserList) {
            if(advenuturePromotionUser.isSubscribed() && advenuturePromotionUser.getAdventure().getId().equals(adventure.getId())) {
                User user = advenuturePromotionUser.getPromotionUser();
                try {
                    emailService.sendNewPromotionNotification(user.getEmail(), promotion.getId());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
        return promotion;
    }

    @Override
    public Promotion addPromotionToBoat(PromotionRequest promotionRequest) {
        return null;
    }

    @Override
    public Promotion addPromotionToVacationHome(PromotionRequest promotionRequest) {
        return null;
    }
}
