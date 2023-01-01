package com.javid.clothingstore.service;


import com.javid.clothingstore.model.Account;
import com.javid.clothingstore.model.Cart;
import com.javid.clothingstore.model.CartLines;
import com.javid.clothingstore.model.User;
import com.javid.clothingstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();

    }

    @Override
    public User createUser(User user) {
        User localUser = userRepository.findByUsername(user.getUsername());

        if(localUser != null) {
            System.out.println("User already exists");
        }

        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);

        Account account = new Account();
        account.setUser(user);
        user.setAccount(account);


        localUser = userRepository.save(user);
       return localUser;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByPassword(String password) {
       return userRepository.findByPassword(password);
    }
}
