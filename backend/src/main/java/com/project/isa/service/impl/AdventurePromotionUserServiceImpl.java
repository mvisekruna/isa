package com.project.isa.service.impl;

import com.project.isa.model.Adventure;
import com.project.isa.model.AdventurePromotionUser;
import com.project.isa.model.User;
import com.project.isa.repository.AdventurePromotionUserRepository;
import com.project.isa.request.AdventurePromotionUserRequest;
import com.project.isa.service.AdventurePromotionUserService;
import com.project.isa.service.AdventureService;
import com.project.isa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdventurePromotionUserServiceImpl implements AdventurePromotionUserService {

    @Autowired
    AdventurePromotionUserRepository adventurePromotionUserRepository;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    AdventureService adventureService;

    @Autowired
    UserService userService;

    @Override
    public AdventurePromotionUser findById(Long id) {
        AdventurePromotionUser adventurePromotionUser = adventurePromotionUserRepository.findById(id).orElseGet(null);
        return adventurePromotionUser;
    }

    @Override
    public List<AdventurePromotionUser> findAll() {
        return adventurePromotionUserRepository.findAll();
    }

    public void subscribe(Long id){
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        AdventurePromotionUser adventurePromotionUser = findById(id);
        adventurePromotionUser.setSubscribed(true);
    }

    public AdventurePromotionUser save(AdventurePromotionUserRequest adventurePromotionUserRequest) {
        AdventurePromotionUser adventurePromotionUser = new AdventurePromotionUser();

        Adventure adventure = adventureService.findById(adventurePromotionUserRequest.getAdventureId());
        adventurePromotionUser.setAdventure(adventure);

        User promotionUser = userService.findById(adventurePromotionUserRequest.getPromotionUserId());
        adventurePromotionUser.setPromotionUser(promotionUser);

        adventurePromotionUser.setSubscribed(true);

        return adventurePromotionUserRepository.save(adventurePromotionUser);
    }


}
