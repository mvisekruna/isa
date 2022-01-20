package com.project.isa.controller;

import com.project.isa.model.PromotionVacationHome;
import com.project.isa.request.PromotionVacationHomeRequest;
import com.project.isa.service.PromotionVacationHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/promotion/vacation/home")
public class PromotionVacationHomeController {

    @Autowired
    PromotionVacationHomeService promotionVacationHomeService;

    @GetMapping("/getOne/{promotionId}")
    public PromotionVacationHome findById(@PathVariable Long promotionId) throws AccessDeniedException {
        return promotionVacationHomeService.findById(promotionId);
    }

    @PostMapping("/addpromotion/tovacationhome")
    @PreAuthorize("hasRole('TUTOR')")
    public PromotionVacationHome addPromotionToVacationHome(@RequestBody PromotionVacationHomeRequest promotionVacationHomeRequest) {
        return promotionVacationHomeService.addPromotionToVacationHome(promotionVacationHomeRequest);
    }

    @GetMapping("/getallvactionhomepromotions")
    @PreAuthorize("hasAnyRole(\"TUTOR\",\"USER\")")
    public List<PromotionVacationHome> loadAllVacationHomePromotions() {
        return promotionVacationHomeService.findAllVacationHomePromotions();
    }

}
