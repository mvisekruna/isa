package com.project.isa.service;

import com.project.isa.model.PromotionVacationHomeUser;
import com.project.isa.model.VacationHome;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromotionVacationHomeUserService {


    PromotionVacationHomeUser findById(Long id);
    List<PromotionVacationHomeUser> findAll();
    List<VacationHome> findAllSubscribed();
    PromotionVacationHomeUser subscribeToPromotions(Long vacationHomeId);
}
