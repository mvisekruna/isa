package com.project.isa.service;

import com.project.isa.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromotionBoatUserService {
    PromotionBoatUser findById(Long id);
    List<PromotionBoatUser> findAll();
    List<PromotionBoat> findAllWithBoatId(Long boatId);
    List<PromotionBoatUser> findAllWithPromotionId(Long promotionId);
    PromotionBoatUser chooseThePromotion(Long promotionId);
    PromotionBoatUser findByBoatId(Long boatId);
    PromotionBoat cancelThePromotion(Long id);
    List<PromotionBoat> findAllPromotionsIChose();
}
