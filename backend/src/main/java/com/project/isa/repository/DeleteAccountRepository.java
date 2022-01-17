package com.project.isa.repository;

import com.project.isa.model.DeleteAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeleteAccountRepository extends JpaRepository<DeleteAccount, Long> {

    List<DeleteAccount> findAllDeleteAccountsByConfirm(boolean confirm);
}
