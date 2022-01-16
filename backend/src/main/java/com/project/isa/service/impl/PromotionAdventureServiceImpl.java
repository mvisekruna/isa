package com.project.isa.service.impl;

import com.project.isa.model.Adventure;
import com.project.isa.model.PromotionAdventureUser;
import com.project.isa.model.PromotionAdventure;
import com.project.isa.model.User;
import com.project.isa.repository.PromotionAdventureRepository;
import com.project.isa.request.PromotionAdventureRequest;
import com.project.isa.service.PromotionAdventureUserService;
import com.project.isa.service.AdventureService;
import com.project.isa.service.PromotionAdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class PromotionAdventureServiceImpl implements PromotionAdventureService {

    @Autowired
    private PromotionAdventureRepository promotionAdventureRepository;

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private PromotionAdventureUserService promotionAdventureUserService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public List<PromotionAdventure> findAllAdventurePromotions(){
        return promotionAdventureRepository.findAll();
    }

    @Override
    public PromotionAdventure findById(Long id) throws AccessDeniedException {
        return promotionAdventureRepository.findById(id).orElseGet(null);
    }

    @Override
    public PromotionAdventure addPromotionToAdventure(PromotionAdventureRequest promotionAdventureRequest) {
        Adventure adventure = adventureService.findById((promotionAdventureRequest.getAdventurePromotionId()));

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User tutor = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        if(!tutor.getId().equals(adventure.getAdventureTutor().getId()) && tutor.isEnabled()==false){
            return null;
        }

        PromotionAdventure promotionAdventure = new PromotionAdventure();

        promotionAdventure.setAdventurePromotion(adventure);
        promotionAdventure.setStartPromo(Timestamp.valueOf(promotionAdventureRequest.getStartPromo()));
        promotionAdventure.setEndPromo(Timestamp.valueOf(promotionAdventureRequest.getEndPromo()));
        promotionAdventure.setDescription(promotionAdventureRequest.getDescription());

        promotionAdventureRepository.save(promotionAdventure);

        List<PromotionAdventureUser> promotionAdventureUserList = promotionAdventureUserService.findAll();
        for(PromotionAdventureUser promotionAdventureUser : promotionAdventureUserList) {
            if(promotionAdventureUser.isSubscribed() && promotionAdventureUser.getAdventure().getId().equals(adventure.getId())) {
                User user = promotionAdventureUser.getPromotionUser();
                try {
                    emailService.sendNewPromotionNotification(user.getEmail(), promotionAdventure.getId());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
        return promotionAdventure;
    }
}
