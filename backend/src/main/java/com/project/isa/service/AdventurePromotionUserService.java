package com.project.isa.service;

import com.project.isa.model.AdventurePromotionUser;

import java.util.List;

public interface AdventurePromotionUserService {
    AdventurePromotionUser findById(Long id);
    List<AdventurePromotionUser> findAll();
}
