package com.javid.clothingstore.repository;

import com.javid.clothingstore.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByUserId(Long id);
}
