package com.project.isa.service;

import com.project.isa.model.Adventure;
import com.project.isa.request.AdventureRequest;

import java.util.List;

public interface AdventureService {
    Adventure findById(Long id);
    List<Adventure> findAll();
    Adventure save(AdventureRequest adventureRequest);
}
