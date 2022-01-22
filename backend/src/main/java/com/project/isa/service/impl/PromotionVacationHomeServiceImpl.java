package com.project.isa.service.impl;

import com.project.isa.model.*;
import com.project.isa.repository.PromotionVacationHomeRepository;
import com.project.isa.request.PromotionVacationHomeRequest;
import com.project.isa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class PromotionVacationHomeServiceImpl implements PromotionVacationHomeService {


    @Autowired
    private PromotionVacationHomeRepository promotionVacationHomeRepository;

    @Autowired
    private VacationHomeService vacationHomeService;

    @Autowired
    private PromotionVacationHomeUserService promotionVacationHomeUserService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserService userService;

    @Override
    public PromotionVacationHome findById(Long id) {
        return promotionVacationHomeRepository.findById(id).orElseGet(null);
    }

    @Override
    public PromotionVacationHome addPromotionToVacationHome(PromotionVacationHomeRequest promotionVacationHomeRequest) {
        VacationHome vacationHome = vacationHomeService.findById((promotionVacationHomeRequest.getVacationHomePromotionId()));

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User vacationHomeOwner = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        if(!vacationHomeOwner.getId().equals(vacationHome.getVacationHomeOwner().getId()) && vacationHomeOwner.isEnabled()==false){
            return null;
        }

        PromotionVacationHome promotionVacationHome = new PromotionVacationHome();

        promotionVacationHome.setVacationHomePromotion(vacationHome);
        promotionVacationHome.setStartPromo(Timestamp.valueOf(promotionVacationHomeRequest.getStartPromo()));
        promotionVacationHome.setEndPromo(Timestamp.valueOf(promotionVacationHomeRequest.getEndPromo()));
        promotionVacationHome.setDescription(promotionVacationHomeRequest.getDescription());
        promotionVacationHome.setNumberOfPromotions(promotionVacationHomeRequest.getNumberOfPromotions());

        promotionVacationHomeRepository.save(promotionVacationHome);

        List<User> subscribedUsers = userService.findAll();
        for(User u: subscribedUsers) {
            List<VacationHome> vacationHomeList = u.getVacationHomes();
            for(VacationHome vh: vacationHomeList){
                if(vh.getId().equals(promotionVacationHome.getVacationHomePromotion().getId())){
                    try {
                        emailService.sendNewPromotionNotification(u.getEmail(), promotionVacationHome.getId());
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return promotionVacationHome;
    }

    @Override
    public List<PromotionVacationHome> findAllVacationHomePromotions() {
        return promotionVacationHomeRepository.findPromotionVacationHomesByNumberOfPromotionsGreaterThan(0);
    }
}
