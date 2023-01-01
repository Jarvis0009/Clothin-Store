package com.javid.clothingstore.repository;

import com.javid.clothingstore.model.Cart;
import com.javid.clothingstore.model.CartLines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository  extends JpaRepository<Cart, Long> {

    Cart save(Cart cart);

Cart findByCartLinesList(Cart cart);
}
