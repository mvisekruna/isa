package com.project.isa.service.impl;

import com.project.isa.model.*;
import com.project.isa.repository.PromotionBoatRepository;
import com.project.isa.request.PromotionBoatRequest;
import com.project.isa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserService userService;

    @Override
    public PromotionBoat findById(Long id) throws AccessDeniedException {
        return promotionBoatRepository.findById(id).orElseGet(null);
    }

    @Override
    public PromotionBoat addPromotionToBoat(PromotionBoatRequest promotionBoatRequest) {
        Boat boat = boatService.findById((promotionBoatRequest.getBoatPromotionId()));

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User boatOwner = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        if(!boatOwner.getId().equals(boat.getBoatOwner().getId()) && boatOwner.isEnabled()==false){
            return null;
        }

        PromotionBoat promotionBoat = new PromotionBoat();

        promotionBoat.setBoatPromotion(boat);
        promotionBoat.setStartPromo(Timestamp.valueOf(promotionBoatRequest.getStartPromo()));
        promotionBoat.setEndPromo(Timestamp.valueOf(promotionBoatRequest.getEndPromo()));
        promotionBoat.setDescription(promotionBoatRequest.getDescription());
        promotionBoat.setNumberOfPromotions(promotionBoatRequest.getNumberOfPromotions());

        promotionBoatRepository.save(promotionBoat);

        List<User> subscribedUsers = userService.findAll();
        for(User u: subscribedUsers) {
            List<Boat> boatList = u.getBoats();
            for(Boat b: boatList) {
                if(b.getId().equals(promotionBoat.getBoatPromotion().getId())){
                    try {
                        emailService.sendNewPromotionNotification(u.getEmail(), promotionBoat.getId());
                    } catch (MessagingException e){
                        e.printStackTrace();
                    }
                }
            }
        }

        return promotionBoat;
    }

    @Override
    public List<PromotionBoat> findAllBoatPromotions() {
        return promotionBoatRepository.findPromotionBoatsByNumberOfPromotionsGreaterThan(0);
    }
}
