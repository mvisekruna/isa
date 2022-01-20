package com.project.isa.repository;

import com.project.isa.model.PromotionVacationHome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionVacationHomeRepository extends JpaRepository<PromotionVacationHome, Long> {
    List<PromotionVacationHome> findPromotionVacationHomesByNumberOfPromotionsGreaterThan(int numberOfPromotions);

}
