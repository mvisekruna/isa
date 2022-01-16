package com.project.isa.service;

import com.project.isa.model.Adventure;
import com.project.isa.model.PromotionAdventureUser;
import com.project.isa.model.PromotionAdventure;
import com.project.isa.request.PromotionAdventureUserRequest;

import java.util.List;

public interface PromotionAdventureUserService {
    PromotionAdventureUser findById(Long id);
    List<PromotionAdventureUser> findAll();
    List<Adventure> findAllSubscribed();
    PromotionAdventureUser subscribeToPromotions(PromotionAdventureUserRequest promotionAdventureUserRequest);
}
