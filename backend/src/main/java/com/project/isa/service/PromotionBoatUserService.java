package com.project.isa.service;

import com.project.isa.model.Boat;
import com.project.isa.model.PromotionBoatUser;
import com.project.isa.request.PromotionBoatUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromotionBoatUserService {

    PromotionBoatUser findById(Long id);
    List<PromotionBoatUser> findAll();
    List<Boat> findAllSubscribed();
    PromotionBoatUser subscribeToPromotions(PromotionBoatUserRequest promotionBoatUserRequest);
}
