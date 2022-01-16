package com.project.isa.service.impl;

import com.project.isa.model.DeleteAccount;
import com.project.isa.model.User;
import com.project.isa.repository.DeleteAccountRepository;
import com.project.isa.repository.UserRepository;
import com.project.isa.request.DeleteAccountRequest;
import com.project.isa.service.DeleteAccountService;
import com.project.isa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class DeleteAccountServiceImpl implements DeleteAccountService {

    @Autowired
    DeleteAccountRepository deleteAccountRepository;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailServiceImpl emailService;


    @Override
    public DeleteAccount findById(Long id) {
        return deleteAccountRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<DeleteAccount> findAll() { return deleteAccountRepository.findAll(); }

    @Override
    public DeleteAccount sendTheRequest(String reason) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        String email = currentUser.getName();
        User u = (User) customUserDetailsService.loadUserByUsername(email);

        DeleteAccount deleteAccount = new DeleteAccount();
        deleteAccount.setUser(u);
        deleteAccount.setReason(reason);
        deleteAccount.setConfirm(false);

        return deleteAccountRepository.save(deleteAccount);
    }

    @Override
    public DeleteAccount deleteApproved(Long deleteAccountId, String reasonToConfirm) {
        DeleteAccount deleteAccount = findById(deleteAccountId);
        deleteAccount.setConfirm(true);
        User user = userService.findById(deleteAccount.getUser().getId());
        user.setEnabled(false);
        userRepository.save(user);

        try {
            emailService.deleteAccountAsync(user.getEmail(), reasonToConfirm);
        } catch (MailException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return deleteAccountRepository.save(deleteAccount);
    }

    @Override
    public void deleteDenied(Long deleteAccountId, String reasonToBeDenied) {
        DeleteAccount deleteAccount = findById(deleteAccountId);
        User user = userService.findById(deleteAccount.getUser().getId());

        try {
            emailService.deleteDenied(user.getEmail(), reasonToBeDenied);
        } catch (MailException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
