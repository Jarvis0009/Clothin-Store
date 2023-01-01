package com.javid.clothingstore.service;

import com.javid.clothingstore.model.Cart;
import com.javid.clothingstore.model.CartLines;

import java.util.List;

public interface CartService {
    Cart updateCart(Cart cart);

    Cart save(Cart cart);
    void clearCart(Cart cart);

    Cart getAllPrices(Cart cart);



    Cart findByCartLinesList(Cart cart);
}
