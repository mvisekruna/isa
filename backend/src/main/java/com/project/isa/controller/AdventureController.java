package com.project.isa.controller;

import com.project.isa.model.Adventure;
import com.project.isa.request.AdventureRequest;
import com.project.isa.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/adventure", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class AdventureController {

    @Autowired
    private AdventureService adventureService;

    @GetMapping("/one/{adventureId}")
    public Adventure loadById(@PathVariable Long adventureId) {
        return adventureService.findById(adventureId);
    }

    @GetMapping("/all")
    public List<Adventure> loadAll() {
        return adventureService.findAll();
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public Adventure save(@RequestBody AdventureRequest adventureRequest) {
        return adventureService.save(adventureRequest);
    }
}
