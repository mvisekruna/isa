package com.project.isa.repository;

import com.project.isa.model.PromotionAdventure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionAdventureRepository extends JpaRepository<PromotionAdventure, Long> {
    List<PromotionAdventure> findPromotionAdventuresByNumberOfPromotionsGreaterThan(int numberOfPromotions);
}
