package com.project.isa.controller;

import com.project.isa.model.Adventure;
import com.project.isa.model.PromotionAdventure;
import com.project.isa.model.PromotionAdventureUser;
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

    @GetMapping("/all/adventures/{adventureId}")
    public List<PromotionAdventure> findAllWithAdventureId(@PathVariable Long adventureId) {
        return promotionAdventureUserService.findAllWithAdventureId(adventureId);
    }

    @GetMapping("/all/promotions/{promotionId}")
    List<PromotionAdventureUser> findAllWithPromotionId(@PathVariable Long promotionId) {
        return promotionAdventureUserService.findAllWithPromotionId(promotionId);
    }

    @PostMapping("/choose/{promotionId}")
    @PreAuthorize("hasRole('USER')")
    public PromotionAdventureUser chooseThePromotion(@PathVariable Long promotionId) {
        return promotionAdventureUserService.chooseThePromotion(promotionId);
    }

    @PostMapping("delete/{id}")
    public ResponseEntity<Object> deleteOne(@PathVariable Long id){
        PromotionAdventureUser u = promotionAdventureUserService.findById(id);
        if (u==null) {
            return ResponseEntity.notFound().build();
        }
        promotionAdventureUserService.delete(u);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/adventure/{adventureId}")
    @PreAuthorize("hasRole('USER')")
    public PromotionAdventureUser findByAdventureId(@PathVariable Long adventureId) {
        return promotionAdventureUserService.findByAdventureId(adventureId);
    }

    @PostMapping("/cancel/{id}")
    @PreAuthorize("hasRole('USER')")
    public PromotionAdventure cancelThePromotion(@PathVariable Long id) {
        return promotionAdventureUserService.cancelThePromotion(id);
    }

    @GetMapping("/allpromotions/ichose")
    @PreAuthorize("hasRole('USER')")
    public List<PromotionAdventure> findAllPromotionsIChose() {
        return promotionAdventureUserService.findAllPromotionsIChose();
    }
}
