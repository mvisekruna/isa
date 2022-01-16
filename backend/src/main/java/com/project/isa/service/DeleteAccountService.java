package com.project.isa.service;

import com.project.isa.model.DeleteAccount;
import com.project.isa.model.User;
import com.project.isa.request.DeleteAccountRequest;

import java.util.List;

public interface DeleteAccountService {
    DeleteAccount findById(Long id);
    List<DeleteAccount> findAll();
    DeleteAccount sendTheRequest(String reasonToDelete);
    DeleteAccount deleteApproved(Long deleteAccountId, String reasonToConfirm);
    void deleteDenied(Long deleteAccountId, String reasonToConfirm);

}
