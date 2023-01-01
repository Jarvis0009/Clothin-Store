package com.javid.clothingstore.service;

import com.javid.clothingstore.model.Account;
import com.javid.clothingstore.model.User;

public interface UserService {
    User findByUsername(String username);

    User findByEmail(String email);

    User findById(Long id);

    User createUser(User user);

    User updateUser(User user);

    User save(User user);

    User findByPassword(String password);




}
