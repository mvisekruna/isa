package com.project.isa.repository;

import com.project.isa.model.PromotionBoat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionBoatRepository extends JpaRepository<PromotionBoat, Long> {
    List<PromotionBoat> findPromotionBoatsByNumberOfPromotionsGreaterThan(int numberOfPromotions);

}
