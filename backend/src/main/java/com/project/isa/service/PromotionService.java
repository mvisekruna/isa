package com.project.isa.service;

import com.project.isa.model.Promotion;
import com.project.isa.request.PromotionRequest;

public interface PromotionService {

    Promotion findById(Long id);
    Promotion addPromotionToAdventure(PromotionRequest promotionRequest);
    Promotion addPromotionToBoat(PromotionRequest promotionRequest);
    Promotion addPromotionToVacationHome(PromotionRequest promotionRequest);

}
