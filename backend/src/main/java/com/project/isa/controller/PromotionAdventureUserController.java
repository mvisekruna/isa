package com.project.isa.controller;

import com.project.isa.model.Adventure;
import com.project.isa.model.PromotionAdventureUser;
import com.project.isa.model.PromotionAdventure;
import com.project.isa.request.PromotionAdventureUserRequest;
import com.project.isa.service.impl.PromotionAdventureUserServiceImpl;
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
@RequestMapping(value = "/promotionadventureuser")
public class PromotionAdventureUserController {

    @Autowired
    PromotionAdventureUserServiceImpl promotionAdventureUserService;

    @GetMapping("/{id}")
    public PromotionAdventureUser findById(@PathVariable Long id) throws AccessDeniedException {
        return promotionAdventureUserService.findById(id);
    }

    @GetMapping("/all")
    public List<PromotionAdventureUser> findAll() {
        return promotionAdventureUserService.findAll();
    }

    @GetMapping("/allsubscribed")
    @PreAuthorize("hasRole('USER')")
    public List<Adventure> findAllSubscribed() {
        return promotionAdventureUserService.findAllSubscribed();
    }

    @PostMapping("/subscribe")
    @PreAuthorize("hasRole('USER')")
    public PromotionAdventureUser subscribeToPromotions(@RequestBody PromotionAdventureUserRequest promotionAdventureUserRequest) {
        return promotionAdventureUserService.subscribeToPromotions(promotionAdventureUserRequest);
    }

    @PostMapping("delete/{id}")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<Object> deleteOne(@PathVariable Long id){
        PromotionAdventureUser u = promotionAdventureUserService.findById(id);
        if (u==null) {
            return ResponseEntity.notFound().build();
        }
        promotionAdventureUserService.delete(u);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/adventure/{adventureId}")
    public PromotionAdventureUser findByAdventureId(@PathVariable Long adventureId) {
        return promotionAdventureUserService.findByAdventureId(adventureId);
    }

    @PostMapping("unsubscribe/{adventureId}")
    public void unsubscribe(@PathVariable Long adventureId){
        promotionAdventureUserService.unsubscribe(adventureId);
    }

}
