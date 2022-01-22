package com.project.isa.service;

import com.project.isa.model.PromotionBoat;
import com.project.isa.request.PromotionBoatRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromotionBoatService {
    PromotionBoat findById(Long id);
    PromotionBoat addPromotionToBoat(PromotionBoatRequest promotionBoatRequest);
    List<PromotionBoat> findAllBoatPromotions();
}
