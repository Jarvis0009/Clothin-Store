package com.javid.clothingstore.repository;

import com.javid.clothingstore.model.Cart;
import com.javid.clothingstore.model.CartLines;
import com.javid.clothingstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartLinesRepository extends JpaRepository<CartLines, Long> {
    List<CartLines> findByCart(Cart cart);

    void deleteById(Long id);




}
