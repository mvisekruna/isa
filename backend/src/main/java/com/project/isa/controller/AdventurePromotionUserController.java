package com.project.isa.controller;

import com.project.isa.model.AdventurePromotionUser;
import com.project.isa.request.AdventurePromotionUserRequest;
import com.project.isa.service.AdventurePromotionUserService;
import com.project.isa.service.impl.AdventurePromotionUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/adventurepromouser")
public class AdventurePromotionUserController {

    @Autowired
    AdventurePromotionUserServiceImpl adventurePromotionUserService;


    @GetMapping
    public List<AdventurePromotionUser> findAll() {
        return adventurePromotionUserService.findAll();
    }

    @GetMapping("/{id}")
    public AdventurePromotionUser findById(@PathVariable Long id) throws AccessDeniedException {
        return adventurePromotionUserService.findById(id);
    }

    @GetMapping("/subscribe/{id}")
    @PreAuthorize("hasRole('USER')")
    public void subscribe(@PathVariable Long id) {
        adventurePromotionUserService.subscribe(id);
    }

    @PostMapping("/save")
    public AdventurePromotionUser save(@RequestBody AdventurePromotionUserRequest adventurePromotionUserRequest) {
        return adventurePromotionUserService.save(adventurePromotionUserRequest);
    }
}
