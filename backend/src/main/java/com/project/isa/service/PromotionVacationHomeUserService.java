package com.project.isa.service;

import com.project.isa.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromotionVacationHomeUserService {
    PromotionVacationHomeUser findById(Long id);
    List<PromotionVacationHomeUser> findAll();
    List<PromotionVacationHome> findAllWithVacationHomeId(Long vacationHomeId);
    List<PromotionVacationHomeUser> findAllWithPromotionId(Long promotionId);
    PromotionVacationHomeUser chooseThePromotion(Long promotionId);
    PromotionVacationHomeUser findByVacationHomeId(Long vacationHomeId);
    PromotionVacationHome cancelThePromotion(Long vacationHomeId);
    List<PromotionVacationHome> findAllPromotionsIChose();

}
