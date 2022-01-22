package com.project.isa.service.impl;

import com.project.isa.model.Adventure;
import com.project.isa.model.Boat;
import com.project.isa.model.Role;
import com.project.isa.model.User;
import com.project.isa.repository.UserRepository;
import com.project.isa.request.UserRequest;
import com.project.isa.request.UserUpdateRequest;
import com.project.isa.service.AdventureService;
import com.project.isa.service.BoatService;
import com.project.isa.service.RoleService;
import com.project.isa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    AdventureService adventureService;

    @Autowired
    BoatService boatService;

    @Override
    public User findById(Long id) throws AccessDeniedException {
        return userRepository.findById(id).orElseGet(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() throws AccessDeniedException {
        return userRepository.findAll();
    }

    @Override
    public User register(UserRequest userRequest) {
        User u = new User();

        u.setEmail(userRequest.getEmail());
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setFirstName(userRequest.getFirstName());
        u.setLastName(userRequest.getLastName());
        u.setAddress(userRequest.getAddress());
        u.setCity(userRequest.getCity());
        u.setState(userRequest.getState());
        u.setFirstLogin(true);
        u.setPhoneNumber(userRequest.getPhoneNumber());
        u.setEnabled(false);

        List<Role> roles = roleService.findByName("ROLE_USER");
        u.setRoles(roles);
        u = this.userRepository.save(u);

        try {
            emailService.sendNotificationAsync(userRequest, u.getId());
        } catch (MailException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return u;
    }

    @Override
    public User registerAdmin(UserRequest userRequest) {
        User u = new User();

        u.setEmail(userRequest.getEmail());
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setFirstName(userRequest.getFirstName());
        u.setLastName(userRequest.getLastName());
        u.setAddress(userRequest.getAddress());
        u.setCity(userRequest.getCity());
        u.setState(userRequest.getState());
        u.setFirstLogin(true);
        u.setPhoneNumber(userRequest.getPhoneNumber());
        u.setEnabled(true);

        List<Role> roles = roleService.findByName("ROLE_ADMIN");
        u.setRoles(roles);
        u = this.userRepository.save(u);

        return u;
    }

    @Override
    public User registrationForOthers(UserRequest userRequest) {
        User u = new User();

        u.setEmail(userRequest.getEmail());
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setFirstName(userRequest.getFirstName());
        u.setLastName(userRequest.getLastName());
        u.setAddress(userRequest.getAddress());
        u.setCity(userRequest.getCity());
        u.setState(userRequest.getState());
        u.setFirstLogin(true);
        u.setPhoneNumber(userRequest.getPhoneNumber());
        u.setEnabled(false);

        List<Role> roles = roleService.findByName(userRequest.getRole());
        u.setRoles(roles);
        u = this.userRepository.save(u);

        try {
            emailService.sendNotificationToAdminAsync(userRequest, u.getId());
        } catch (MailException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return u;
    }

    @Override
    public User update(UserUpdateRequest userUpdateRequest) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        String email = currentUser.getName();
        User u = (User) customUserDetailsService.loadUserByUsername(email);
        u.setFirstName(userUpdateRequest.getFirstName());
        u.setLastName(userUpdateRequest.getLastName());
        u.setAddress(userUpdateRequest.getAddress());
        u.setCity(userUpdateRequest.getCity());
        u.setState(userUpdateRequest.getState());
        u.setPhoneNumber(userUpdateRequest.getPhoneNumber());

        return userRepository.save(u);
    }

    @Override
    public List<User> findAllTutors() {
        List<User> users = findAll();
        List<User> tutors = new ArrayList<User>();
        for(User u: users) {
            List<Role> userRoles = (List<Role>) u.getRoles();
            if(userRoles.get(0).getName().contains("ROLE_TUTOR")){
                tutors.add(u);
            }
        }
        return tutors;
    }

    @Override
    public User activateAccount(String email) {
        User user = findByEmail(email);
        user.setEnabled(true);
        System.out.println("aktiviran");
        return userRepository.save(user);
    }

    @Override
    public User deactivateAccount(String email){
        User user = findByEmail(email);
        user.setEnabled(false);
        System.out.println("obrisan");
        return userRepository.save(user);
    }


    /**ADVENTURE*******/
    @Override
    public User subscribeToAdventurePromotions(Long adventureId) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User temp = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());
        Adventure adventure = adventureService.findById(adventureId);

        List<Adventure> adventureList = temp.getAdventures();
        for(Adventure a: adventureList){
            if(a.getId().equals(adventureId)){
               return null;
            }
        }
        adventureList.add(adventure);
        temp.setAdventures(adventureList);

        return userRepository.save(temp);
    }

    @Override
    public void cancelMyAdventureSubscription(Long adventureId) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());
        Adventure adventure = adventureService.findById(adventureId);
        List<Adventure> adventureList = user.getAdventures();
        adventureList.remove(adventure);

        userRepository.save(user);
    }

    @Override
    public List<Adventure> findMySubscribedAdventures() { //FIND ALL ADVENTURES IM SUBSCRIBED TO
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<Adventure> adventureList = user.getAdventures();

        return adventureList;
    }

    /**BOAT*******/
    @Override
    public User subscribeToBoatPromotions(Long boatId) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User temp = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());
        Boat boat = boatService.findById(boatId);

        List<Boat> boatList = temp.getBoats();
        for(Boat b: boatList){
            if(b.getId().equals(boatId)){
                return null;
            }
        }
        boatList.add(boat);
        temp.setBoats(boatList);

        return userRepository.save(temp);
    }

    @Override
    public void cancelMyBoatSubscription(Long boatId) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());
        Boat boat = boatService.findById(boatId);
        List<Boat> boatList = user.getBoats();
        boatList.remove(boat);

        userRepository.save(user);
    }

    @Override
    public List<Boat> findMySubscribedBoats() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) customUserDetailsService.loadUserByUsername(currentUser.getName());

        List<Boat> boatList = user.getBoats();

        return boatList;
    }

    /**VACATION HOME*******/



}
