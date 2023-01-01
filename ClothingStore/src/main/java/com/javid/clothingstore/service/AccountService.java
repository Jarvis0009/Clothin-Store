package com.javid.clothingstore.service;

import com.javid.clothingstore.model.Account;
import com.javid.clothingstore.model.Order;

public interface AccountService  {

     boolean saveAccount(Account account);


     Account updateAccount(Account account);

    Account findAccountByUserId(Long id);


}
