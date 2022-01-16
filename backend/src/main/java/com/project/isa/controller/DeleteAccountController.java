package com.project.isa.controller;

import com.project.isa.model.DeleteAccount;
import com.project.isa.service.DeleteAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/deleteaccount", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class DeleteAccountController {

    @Autowired
    private DeleteAccountService deleteAccountService;

    @GetMapping("/one/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public DeleteAccount findById(Long id) {
        return deleteAccountService.findById(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DeleteAccount> findAll() { return deleteAccountService.findAll(); }

    @PostMapping("/sendtherequest")
    @PreAuthorize("hasAnyRole(\"TUTOR\",\"USER\",\"VACATION_HOME_OWNER\",\"BOAT_OWNER\")")
    public DeleteAccount sendTheRequest(@RequestBody String reason) {
        return deleteAccountService.sendTheRequest(reason);
    }

    @PostMapping("/deleteapproved/{deleteAccountId}")
    @PreAuthorize("hasRole('ADMIN')")
    public DeleteAccount deleteApproved(@PathVariable Long deleteAccountId, @RequestBody String reasonToConfirm) {
        return deleteAccountService.deleteApproved(deleteAccountId, reasonToConfirm);
    }

    @PostMapping("/deletedenied/{deleteAccountId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDenied(@PathVariable Long deleteAccountId, @RequestBody String reasonToBeDenied) {
        deleteAccountService.deleteDenied(deleteAccountId, reasonToBeDenied);
    }
}
