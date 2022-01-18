package com.project.isa.controller;

import com.project.isa.model.PromotionBoat;
import com.project.isa.request.PromotionBoatRequest;
import com.project.isa.service.PromotionBoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/promotion/boat")
public class PromotionBoatController {

    @Autowired
    PromotionBoatService promotionBoatService;

    @GetMapping("/getOne/{promotionId}")
    public PromotionBoat findById(@PathVariable Long promotionId) throws AccessDeniedException {
        return promotionBoatService.findById(promotionId);
    }

    @PostMapping("/addpromotion/toboat")
    public PromotionBoat addPromotionToBoat(@RequestBody PromotionBoatRequest promotionBoatRequest) {
        return promotionBoatService.addPromotionToBoat(promotionBoatRequest);
    }

    @GetMapping("/getallboatpromotions")
    public List<PromotionBoat> loadAllBoatPromotions() {
        return promotionBoatService.findAllBoatPromotions();
    }
}
