package com.project.isa.controller;

import com.project.isa.model.Boat;
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
    }

    @GetMapping("/all")
    public List<PromotionBoatUser> findAll() {
        return promotionBoatUserService.findAll();
    }

    @GetMapping("/allsubscribed")
    @PreAuthorize("hasRole('USER')")
    public List<Boat> findAllSubscribed() {
        return promotionBoatUserService.findAllSubscribed();
    }

    @PostMapping("/subscribe/{boatId}")
   // @PreAuthorize("hasRole('ROLE_USER')")
    public PromotionBoatUser subscribeToPromotions(@PathVariable Long boatId) {
        return promotionBoatUserService.subscribeToPromotions(boatId);
    }

    @PostMapping("delete/{id}")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<Object> deleteOne(@PathVariable Long id){
        PromotionBoatUser u = promotionBoatUserService.findById(id);
        if (u==null) {
            return ResponseEntity.notFound().build();
        }
        promotionBoatUserService.delete(u);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/boat/{boatId}")
    public PromotionBoatUser findByBoatId(@PathVariable Long boatId) {
        return promotionBoatUserService.findByBoatId(boatId);
    }

    @PostMapping("unsubscribe/{boatId}")
    public void unsubscribe(@PathVariable Long boatId){
        promotionBoatUserService.unsubscribe(boatId);
    }
}
