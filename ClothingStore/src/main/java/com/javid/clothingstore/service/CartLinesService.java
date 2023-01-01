package com.javid.clothingstore.service;

import com.javid.clothingstore.model.Cart;
import com.javid.clothingstore.model.CartLines;
import com.javid.clothingstore.model.Product;
import com.javid.clothingstore.model.User;

import java.util.List;

public interface CartLinesService {
    CartLines findById(Long id);

    CartLines saveCartLines(CartLines cartLines);

    CartLines updateCartLines(CartLines cartLines);

    boolean removeCartLines(CartLines cartLines);

    void deleteById(Long id);



//    CartLines findByProduct(Product product);
//    CartLines findByCartLineProductName(String username);

    CartLines addProductToCartLines(Product product, User user, int quantity);

    List<CartLines> findByCart(Cart cart);






}
