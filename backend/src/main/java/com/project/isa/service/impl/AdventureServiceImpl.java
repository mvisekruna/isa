package com.project.isa.service.impl;

import com.project.isa.model.Adventure;
import com.project.isa.model.User;
import com.project.isa.repository.AdventureRepository;
import com.project.isa.request.AdventureRequest;
import com.project.isa.service.AdventureService;
import com.project.isa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdventureServiceImpl implements AdventureService {

    @Autowired
    private AdventureRepository adventureRepository;

    @Autowired
    private UserService userService;

    @Override
    public Adventure findById(Long id) {
        return adventureRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Adventure> findAll() {
        return adventureRepository.findAll();
    }

    @Override
    public Adventure save(AdventureRequest adventureRequest) {
        Adventure a = new Adventure();

        a.setAdventureName(adventureRequest.getAdventureName());
        a.setAdventureAddress(adventureRequest.getAdventureAddress());
        a.setAdventureDescription(adventureRequest.getAdventureDescription());
        a.setAdventurePrice(adventureRequest.getAdventurePrice());
        a.setAdventureReview(adventureRequest.getAdventureReview());
        User adventureTutor = userService.findById(adventureRequest.getAdventureTutor());
        a.setAdventureTutor(adventureTutor);

        return adventureRepository.save(a);
    }
}
