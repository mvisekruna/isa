package com.project.isa.service.impl;

import com.project.isa.model.*;
import com.project.isa.repository.PromotionBoatRepository;
import com.project.isa.request.PromotionBoatRequest;
import com.project.isa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class PromotionBoatServiceImpl implements PromotionBoatService {

    @Autowired
    private PromotionBoatRepository promotionBoatRepository;

    @Autowired
    private BoatService boatService;

    @Autowired
    private PromotionBoatUserService promotionBoatUserService;

    @Autowired
    private EmailServiceImpl emailService;


    @Override
    public PromotionBoat findById(Long id) throws AccessDeniedException {
        return promotionBoatRepository.findById(id).orElseGet(null);
    }

    @Override
    public PromotionBoat addPromotionToBoat(PromotionBoatRequest promotionBoatRequest) {

        Boat boat = boatService.findById((promotionBoatRequest.getBoatPromotionId()));

        PromotionBoat promotionBoat = new PromotionBoat();

        promotionBoat.setBoatPromotion(boat);
        promotionBoat.setStartPromo(Timestamp.valueOf(promotionBoatRequest.getStartPromo()));
        promotionBoat.setEndPromo(Timestamp.valueOf(promotionBoatRequest.getEndPromo()));
        promotionBoat.setDescription(promotionBoatRequest.getDescription());

        promotionBoatRepository.save(promotionBoat);

        List<PromotionBoatUser> promotionBoatUserList = promotionBoatUserService.findAll();
        for(PromotionBoatUser promotionBoatUser : promotionBoatUserList) {
            if(promotionBoatUser.isSubscribed() && promotionBoatUser.getBoat().getId().equals(boat.getId())) {
                User user = promotionBoatUser.getPromotionUser();
                try {
                    emailService.sendNewPromotionNotification(user.getEmail(), promotionBoat.getId());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
        return promotionBoat;
    }

    @Override
    public List<PromotionBoat> findAllBoatPromotions() {
        return promotionBoatRepository.findAll();
    }
}
