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

    @GetMapping("/allsubscribed")
    @PreAuthorize("hasRole('USER')")
    public List<VacationHome> findAllSubscribed() {
        return promotionVacationHomeUserService.findAllSubscribed();
    }

    @PostMapping("/subscribe/{vacationHomeId}")
    // @PreAuthorize("hasRole('ROLE_USER')")
    public PromotionVacationHomeUser subscribeToPromotions(@PathVariable Long vacationHomeId) {
        return promotionVacationHomeUserService.subscribeToPromotions(vacationHomeId);
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

    @PostMapping("unsubscribe/{vacationHomeId}")
    public void unsubscribe(@PathVariable Long vacationHomeId){
        promotionVacationHomeUserService.unsubscribe(vacationHomeId);
    }
}
