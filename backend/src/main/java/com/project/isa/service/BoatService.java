package com.project.isa.service;

import com.project.isa.model.Boat;
import com.project.isa.request.BoatRequest;

import java.util.List;

public interface BoatService {
    Boat findById(Long id);
    List<Boat> findAll();
    Boat save(BoatRequest boatRequest);


}
