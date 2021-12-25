package com.project.isa.service.impl;

import com.project.isa.model.Role;
import com.project.isa.model.User;
import com.project.isa.repository.UserRepository;
import com.project.isa.request.UserRequest;
import com.project.isa.service.RoleService;
import com.project.isa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
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
        u.setFirstName(userRequest.getFirstname());
        u.setLastName(userRequest.getLastname());
        u.setAddress(userRequest.getAddress());
        u.setCity(userRequest.getCity());
        u.setState(userRequest.getState());
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

    public User activateAccount(String email) {
        User user = findByEmail(email);
        user.setEnabled(true);
        System.out.println("aktiviran");
        return userRepository.save(user);
    }
}
