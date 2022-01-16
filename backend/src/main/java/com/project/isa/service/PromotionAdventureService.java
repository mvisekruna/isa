package com.project.isa.service;

import com.project.isa.model.PromotionAdventure;
import com.project.isa.request.PromotionAdventureRequest;

import java.util.List;

public interface PromotionAdventureService {

    PromotionAdventure findById(Long id);
    PromotionAdventure addPromotionToAdventure(PromotionAdventureRequest promotionAdventureRequest);
    List<PromotionAdventure> findAllAdventurePromotions();

}
