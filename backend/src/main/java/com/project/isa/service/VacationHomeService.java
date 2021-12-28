package com.project.isa.service;

import com.project.isa.model.VacationHome;
import com.project.isa.request.VacationHomeRequest;

import java.util.List;

public interface VacationHomeService {
    VacationHome findById(Long id);
    List<VacationHome> findAll();
    VacationHome save(VacationHomeRequest vacationHomeRequest);
}
