package com.javid.clothingstore.service;

import com.javid.clothingstore.model.Account;
import com.javid.clothingstore.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean saveAccount(Account account) {
      accountRepository.saveAndFlush(account);
        return true;
    }

    @Override
    public Account updateAccount(Account account) {
        return accountRepository.saveAndFlush(account);
    }

    @Override
    public Account findAccountByUserId(Long id) {
        return accountRepository.findAccountByUserId(id);
    }
}
