package com.project.isa.controller;

import com.project.isa.model.Boat;
import com.project.isa.request.BoatRequest;
import com.project.isa.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/boat", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class BoatController {

    @Autowired
    private BoatService boatService;

    @GetMapping("/one/{boatId}")
    public Boat loadById(@PathVariable Long boatId) {
        return boatService.findById(boatId);
    }

    @GetMapping("/all")
    public List<Boat> loadAll() {
        return boatService.findAll();
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public Boat save(@RequestBody BoatRequest boatRequest) {
        return boatService.save(boatRequest);
    }

}
