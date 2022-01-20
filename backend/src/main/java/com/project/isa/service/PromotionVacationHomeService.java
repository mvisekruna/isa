package com.project.isa.service;

import com.project.isa.model.PromotionVacationHome;
import com.project.isa.request.PromotionVacationHomeRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromotionVacationHomeService {


    PromotionVacationHome findById(Long id);
    PromotionVacationHome addPromotionToVacationHome(PromotionVacationHomeRequest promotionVacationHomeRequest);
    List<PromotionVacationHome> findAllVacationHomePromotions();
}
