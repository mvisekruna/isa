package com.project.isa.controller;

import javax.ws.rs.core.Context;

import com.project.isa.model.User;
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

    @PostMapping("/{email}")
    public ResponseEntity<User> findByEmail(@RequestBody String email, @Context HttpServletRequest request){
        User user = userService.findByEmail(email);
        HttpSession session = request.getSession();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public User loadById(@PathVariable Long userId) {
        return this.userService.findById(userId);
    }

    @GetMapping("/user/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> loadAll() {
        return this.userService.findAll();
    }

    @GetMapping("/tutors")
    public List<User> loadAllTutors() {
        return userService.findAllTutors(); }

    @PostMapping("/updateuser")
    //@PreAuthorize("hasRole('USER')")
    public User updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.update(userUpdateRequest);
    }

    @PostMapping("/changepassword")
    public User changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        return customUserDetailsService.changePassword(changePasswordRequest.getOldPassword(), changePasswordRequest.getNewPassword());
    }

    @RequestMapping(path = "/activateacc/{id}", method = RequestMethod.GET)
    public String activateAccount(@PathVariable int id) {
        User u = userService.findById(Long.valueOf(id));
        userService.activateAccount(u.getEmail());
        return u.getEmail();
    }

//    @GetMapping("/deleteaccount")
//    public User deleteAccount() {
//        return userService.deleteAccount();
//    }

    @RequestMapping(path = "/deactivateacc/{id}", method = RequestMethod.GET)
    public String deactivateAccount(@PathVariable int id) {
        User u = userService.findById(Long.valueOf(id));
        userService.deactivateAccount(u.getEmail());
        return u.getEmail();
    }




    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }
}
