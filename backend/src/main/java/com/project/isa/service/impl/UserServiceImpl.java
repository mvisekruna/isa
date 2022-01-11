package com.project.isa.service.impl;

import com.project.isa.model.Role;
import com.project.isa.model.User;
import com.project.isa.repository.UserRepository;
import com.project.isa.request.UserRequest;
import com.project.isa.request.UserUpdateRequest;
import com.project.isa.service.RoleService;
import com.project.isa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id) throws AccessDeniedException {
        return userRepository.findById(id).orElseGet(null);
    }

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
            emailService.sendNotificaitionAsync(userRequest, u.getId());
        } catch (MailException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
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

    public User activateAccount(String email) {
        User user = findByEmail(email);
        user.setEnabled(true);
        System.out.println("aktiviran");
        return userRepository.save(user);
    }

    public User deleteAccount() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        String email = currentUser.getName();
        User u = (User) customUserDetailsService.loadUserByUsername(email);
        try {
            emailService.deleteAccountAsync(u);
        } catch (MailException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return u;
    }

    public User deactivateAccount(String email){
        User user = findByEmail(email);
        user.setEnabled(false);
        System.out.println("obrisan");
        return userRepository.save(user);
    }
}
