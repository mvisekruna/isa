package com.project.isa.controller;

import javax.ws.rs.core.Context;

import com.project.isa.model.Adventure;
import com.project.isa.model.Boat;
import com.project.isa.model.User;
import com.project.isa.model.VacationHome;
import com.project.isa.request.ChangePasswordRequest;
import com.project.isa.request.UserUpdateRequest;
import com.project.isa.service.impl.CustomUserDetailsService;
import com.project.isa.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public User loadById(@PathVariable Long userId) {
        return this.userService.findById(userId);
    }

    @PostMapping("/{email}")
    public ResponseEntity<User> findByEmail(@RequestBody String email, @Context HttpServletRequest request){
        User user = userService.findByEmail(email);
        HttpSession session = request.getSession();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/user/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> loadAll() {
        return this.userService.findAll();
    }

    @PostMapping("/updateuser")
    //@PreAuthorize("hasRole('USER')")
    public User updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.update(userUpdateRequest);
    }

    @GetMapping("/tutors")
    public List<User> loadAllTutors() {
        return userService.findAllTutors(); }

    @RequestMapping(path = "/activateacc/{id}", method = RequestMethod.GET)
    public String activateAccount(@PathVariable int id) {
        User u = userService.findById(Long.valueOf(id));
        userService.activateAccount(u.getEmail());
        return u.getEmail();
    }

    @RequestMapping(path = "/deactivateacc/{id}", method = RequestMethod.GET)
    public String deactivateAccount(@PathVariable int id) {
        User u = userService.findById(Long.valueOf(id));
        userService.deactivateAccount(u.getEmail());
        return u.getEmail();
    }

    @PostMapping("/changepassword")
    public User changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        return customUserDetailsService.changePassword(changePasswordRequest.getOldPassword(), changePasswordRequest.getNewPassword());
    }

    /**ADVENTURE*******/
    @PostMapping("adventure/subscribe/{adventureId}")
    @PreAuthorize("hasRole('USER')")
    public User subscribeToAdventurePromotions(@PathVariable Long adventureId) {
        return userService.subscribeToAdventurePromotions(adventureId);
    }

    @PostMapping("adventure/cancel/{adventureId}")
    @PreAuthorize("hasRole('USER')")
    public void cancelMyAdventureSubscription(@PathVariable Long adventureId) {
        userService.cancelMyAdventureSubscription(adventureId);
    }

    @GetMapping("adventure/all/subscribed")
    @PreAuthorize("hasRole('USER')")
    public List<Adventure> findMySubscribedAdventures() {
        return userService.findMySubscribedAdventures();
    }


    /**BOAT*******/
    @PostMapping("boat/subscribe/{boatId}")
    @PreAuthorize("hasRole('USER')")
    public User subscribeToBoatPromotions(@PathVariable Long boatId) {
        return userService.subscribeToBoatPromotions(boatId);
    }

    @PostMapping("boat/cancel/{boatId}")
    @PreAuthorize("hasRole('USER')")
    public void cancelMyBoatSubscription(@PathVariable Long boatId) {
        userService.cancelMyBoatSubscription(boatId);
    }

    @GetMapping("boat/all/subscribed")
    @PreAuthorize("hasRole('USER')")
    public List<Boat> findMySubscribedBoats() {
        return userService.findMySubscribedBoats();
    }

    /**VACATION HOME*******/

    @PostMapping("vacationhome/subscribe/{vacationHomeId}")
    @PreAuthorize("hasRole('USER')")
    public User subscribeToVacationHomePromotions(@PathVariable Long vacationHomeId){
        return userService.subscribeToVacationHomePromotions(vacationHomeId);
    }

    @PostMapping("vacationhome/cancel/{vacationHomeId}")
    @PreAuthorize("hasRole('USER')")
    public void cancelMyVacationHomeSubscription(@PathVariable Long vacationHomeId){
        userService.cancelMyVacationHomeSubscription(vacationHomeId);
    }

    @GetMapping("vacationhome/all/subscribed")
    @PreAuthorize("hasRole('USER')")
    public List<VacationHome> findMySubscribedVacationHomes() {
        return userService.findMySubscribedVacationHomes();
    }

    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }
}
