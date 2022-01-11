package com.project.isa.controller;

import com.project.isa.model.Promotion;
import com.project.isa.request.PromotionRequest;
import com.project.isa.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/promotion")
public class PromotionController {

    @Autowired
    PromotionService promotionService;

    @GetMapping("/getOne/{promotionId}")
    public Promotion findById(@PathVariable Long promotionId) throws AccessDeniedException {
        return promotionService.findById(promotionId);
    }

    @PostMapping("/addpromotion/toadventure")
    @PreAuthorize("hasRole('ADMIN')")
    public Promotion addPromotionToAdventure(@RequestBody PromotionRequest promotionRequest) {
        return promotionService.addPromotionToAdventure(promotionRequest);
    }
}
