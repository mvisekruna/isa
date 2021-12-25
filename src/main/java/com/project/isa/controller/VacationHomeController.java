package com.project.isa.controller;

import com.project.isa.model.VacationHome;
import com.project.isa.request.VacationHomeRequest;
import com.project.isa.service.VacationHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vacationhome", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class VacationHomeController {

    @Autowired
    private VacationHomeService vacationHomeService;

    @GetMapping("/one/{vacationHomeId}")
    public VacationHome loadById(@PathVariable Long vacationHomeId) {
        return vacationHomeService.findById(vacationHomeId);
    }

    @GetMapping("/all")
    public List<VacationHome> loadAll() {
        return vacationHomeService.findAll();
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public VacationHome save(@RequestBody VacationHomeRequest vacationHomeRequest) {
        return vacationHomeService.save(vacationHomeRequest);
    }

}
