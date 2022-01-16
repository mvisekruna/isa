package com.project.isa.controller;

import com.project.isa.model.PromotionAdventure;
import com.project.isa.request.PromotionAdventureRequest;
import com.project.isa.service.PromotionAdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/promotion")
public class PromotionAdventureController {

    @Autowired
    PromotionAdventureService promotionAdventureService;

    @GetMapping("/getOne/{promotionId}")
    public PromotionAdventure findById(@PathVariable Long promotionId) throws AccessDeniedException {
        return promotionAdventureService.findById(promotionId);
    }

    @PostMapping("/addpromotion/toadventure")
    @PreAuthorize("hasRole('TUTOR')")
    public PromotionAdventure addPromotionToAdventure(@RequestBody PromotionAdventureRequest promotionAdventureRequest) {
        return promotionAdventureService.addPromotionToAdventure(promotionAdventureRequest);
    }

    @GetMapping("/getalladventurepromotions")
    @PreAuthorize("hasAnyRole(\"TUTOR\",\"USER\")")
    public List<PromotionAdventure> loadAllAdventurePromotions() {
        return promotionAdventureService.findAllAdventurePromotions();
    }
}
