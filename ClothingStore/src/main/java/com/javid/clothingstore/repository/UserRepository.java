package com.javid.clothingstore.repository;

import com.javid.clothingstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByAccountId(Long id);

    User findByEmail(String email);

    User findByPassword(String password);
}
