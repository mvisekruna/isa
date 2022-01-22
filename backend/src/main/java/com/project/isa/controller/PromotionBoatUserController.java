package com.project.isa.controller;

import com.project.isa.model.Boat;
import com.project.isa.model.PromotionBoat;
import com.project.isa.model.PromotionBoatUser;
import com.project.isa.request.PromotionBoatUserRequest;
import com.project.isa.service.impl.PromotionBoatUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/promotionboatuser")
public class PromotionBoatUserController {

    @Autowired
    PromotionBoatUserServiceImpl promotionBoatUserService;

    @GetMapping("/{id}")
    public PromotionBoatUser findById(@PathVariable Long id) throws AccessDeniedException {
        return promotionBoatUserService.findById(id);
    } //ima

    @GetMapping("/all")
    public List<PromotionBoatUser> findAll() {
        return promotionBoatUserService.findAll();
    } //ima

    @GetMapping("/all/boats/{boatId}")
    public List<PromotionBoat> findAllWithBoatId(@PathVariable Long boatId){ //ima
        return promotionBoatUserService.findAllWithBoatId(boatId);
    }

    @GetMapping("/all/promotions/{promotionId}")
    public List<PromotionBoatUser> findAllWithPromotionId(@PathVariable Long promotionId){ //ima
        return promotionBoatUserService.findAllWithPromotionId(promotionId);
    }

    @PostMapping("/choose/{promotionId}")
    @PreAuthorize("hasRole('USER')")
    public PromotionBoatUser chooseThePromotion(@PathVariable Long promotionId){ //ima
        return promotionBoatUserService.chooseThePromotion(promotionId);
    }

    @PostMapping("delete/{id}")
    public ResponseEntity<Object> deleteOne(@PathVariable Long id){
        PromotionBoatUser u = promotionBoatUserService.findById(id);
        if (u==null) {
            return ResponseEntity.notFound().build();
        }
        promotionBoatUserService.delete(u);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    } //ima

    @GetMapping("/boat/{boatId}")
    @PreAuthorize("hasRole('USER')")
    public PromotionBoatUser findByBoatId(@PathVariable Long boatId) { //ima
        return promotionBoatUserService.findByBoatId(boatId);
    }

    @PostMapping("/cancel/{id}")
    @PreAuthorize("hasRole('USER')")
    public PromotionBoat cancelThePromotion(@PathVariable Long id) { //ima
        return promotionBoatUserService.cancelThePromotion(id);
    }

    @GetMapping("/allpromotions/ichose")
    @PreAuthorize("hasRole('USER')")
    List<PromotionBoat> findAllPromotionsIChose() {
        return  promotionBoatUserService.findAllPromotionsIChose();
    }
}
