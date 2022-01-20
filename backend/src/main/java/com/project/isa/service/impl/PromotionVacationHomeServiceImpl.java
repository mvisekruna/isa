package com.project.isa.service.impl;

import com.project.isa.model.*;
import com.project.isa.repository.PromotionVacationHomeRepository;
import com.project.isa.request.PromotionVacationHomeRequest;
import com.project.isa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Override
    public PromotionVacationHome findById(Long id) {
        return promotionVacationHomeRepository.findById(id).orElseGet(null);
    }

    @Override
    public PromotionVacationHome addPromotionToVacationHome(PromotionVacationHomeRequest promotionVacationHomeRequest) {

        VacationHome vacationHome = vacationHomeService.findById((promotionVacationHomeRequest.getVacationHomePromotionId()));

        PromotionVacationHome promotionVacationHome = new PromotionVacationHome();

        promotionVacationHome.setVacationHomePromotion(vacationHome);
        promotionVacationHome.setStartPromo(Timestamp.valueOf(promotionVacationHomeRequest.getStartPromo()));
        promotionVacationHome.setEndPromo(Timestamp.valueOf(promotionVacationHomeRequest.getEndPromo()));
        promotionVacationHome.setDescription(promotionVacationHomeRequest.getDescription());

        promotionVacationHomeRepository.save(promotionVacationHome);

        List<PromotionVacationHomeUser> promotionVacationHomeUserList = promotionVacationHomeUserService.findAll();
        for(PromotionVacationHomeUser promotionVacationHomeUser : promotionVacationHomeUserList) {
            if(promotionVacationHomeUser.isSubscribed() && promotionVacationHomeUser.getVacationHome().getId().equals(vacationHome.getId())) {
                User user = promotionVacationHomeUser.getPromotionUser();
                try {
                    emailService.sendNewPromotionNotification(user.getEmail(), promotionVacationHomeUser.getId());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
        return promotionVacationHome;    }

    @Override
    public List<PromotionVacationHome> findAllVacationHomePromotions() {
        return promotionVacationHomeRepository.findPromotionVacationHomesByNumberOfPromotionsGreaterThan(0);
    }
}
