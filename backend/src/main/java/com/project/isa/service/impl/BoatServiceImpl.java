package com.project.isa.service.impl;

import com.project.isa.model.Boat;
import com.project.isa.model.User;
import com.project.isa.repository.BoatRepository;
import com.project.isa.request.BoatRequest;
import com.project.isa.service.BoatService;
import com.project.isa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoatServiceImpl implements BoatService {

    @Autowired
    private BoatRepository boatRepository;

    @Autowired
    private UserService userService;

    @Override
    public Boat findById(Long id) throws AccessDeniedException {
        return boatRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Boat> findAll() {
        return boatRepository.findAll();
    }

    @Override
    public Boat save(BoatRequest boatRequest) {
        Boat b = new Boat();

        b.setBoatName(boatRequest.getBoatName());
        b.setBoatLocation(boatRequest.getBoatLocation());
        b.setBoatDescription(boatRequest.getBoatDescription());
        b.setBoatPrice(boatRequest.getBoatPrice());
        b.setBoatReview(boatRequest.getBoatReview());
        User boatOwner = userService.findById(boatRequest.getBoatOwner());
        b.setBoatOwner(boatOwner);

        return boatRepository.save(b);
    }
}
