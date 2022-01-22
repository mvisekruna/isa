package com.project.isa.controller;

import com.project.isa.model.*;
import com.project.isa.service.impl.PromotionVacationHomeUserServiceImpl;
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
@RequestMapping(value = "/promotionvacationhomeuser")
public class PromotionVacationHomeUserController {

    @Autowired
    PromotionVacationHomeUserServiceImpl promotionVacationHomeUserService;

    @GetMapping("/{id}")
    public PromotionVacationHomeUser findById(@PathVariable Long id) throws AccessDeniedException {
        return promotionVacationHomeUserService.findById(id);
    }

    @GetMapping("/all")
    public List<PromotionVacationHomeUser> findAll() {
        return promotionVacationHomeUserService.findAll();
    }

    @GetMapping("/all/vacationhomes/{vacationHomeId}")
    public List<PromotionVacationHome> findAllWithVacationHomeId(@PathVariable Long vacationHomeId) {
        return promotionVacationHomeUserService.findAllWithVacationHomeId(vacationHomeId);
    }

    @GetMapping("/all/promotions/{promotionId}")
    public List<PromotionVacationHomeUser> findAllWithPromotionId(@PathVariable Long promotionId){
        return promotionVacationHomeUserService.findAllWithPromotionId(promotionId);
    }

    @PostMapping("/choose/{promotionId}")
    @PreAuthorize("hasRole('USER')")
    public PromotionVacationHomeUser chooseThePromotion(@PathVariable Long promotionId){
        return promotionVacationHomeUserService.chooseThePromotion(promotionId);
    }

    @PostMapping("delete/{id}")
    public ResponseEntity<Object> deleteOne(@PathVariable Long id){
        PromotionVacationHomeUser u = promotionVacationHomeUserService.findById(id);
        if (u==null) {
            return ResponseEntity.notFound().build();
        }
        promotionVacationHomeUserService.delete(u);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/vacation/home/{vacationHomeId}")
    public PromotionVacationHomeUser findByVacationHomeId(@PathVariable Long vacationHomeId) {
        return promotionVacationHomeUserService.findByVacationHomeId(vacationHomeId);
    }

    @PostMapping("/cancel/{id}")
    @PreAuthorize("hasRole('USER')")
    public PromotionVacationHome cancelThePromotion(@PathVariable Long id){
        return promotionVacationHomeUserService.cancelThePromotion(id);
    }

    @GetMapping("/allpromotions/ichose")
    @PreAuthorize("hasRole('USER')")
    public List<PromotionVacationHome> findAllPromotionsIChose(){
        return promotionVacationHomeUserService.findAllPromotionsIChose();
    }
}
