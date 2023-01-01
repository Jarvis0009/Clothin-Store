package com.javid.clothingstore.service;

import com.javid.clothingstore.model.Cart;
import com.javid.clothingstore.model.CartLines;
import com.javid.clothingstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartLinesService cartLinesService;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart updateCart(Cart cart) {
        BigDecimal cartTotal = new BigDecimal(0);

        List<CartLines> cartLinesList = cartLinesService.findByCart(cart);

        for (CartLines cartLines: cartLinesList ) {
            if(cartLines.getProduct().getInStockNumber() > 0) {
                cartLinesService.updateCartLines(cartLines);
                cartTotal = cartTotal.add(cartLines.getCartLineProductTotal());
            }

        }
        cart.setGrandTotal(cartTotal);
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public void clearCart(Cart cart) {
        List<CartLines> cartLinesList = cartLinesService.findByCart(cart);

        for (CartLines cartLines: cartLinesList) {
            cartLines.setCart(null);
            cart.setCartLinesList(null);
            cartLinesService.saveCartLines(cartLines);

            cart.setGrandTotal(new BigDecimal(0));
            cartRepository.save(cart);
        }
    }

    @Override
    public Cart save(Cart cart) {
      return cartRepository.save(cart);
    }

    @Override
    public Cart getAllPrices(Cart cart) {
        BigDecimal cartTotal = new BigDecimal(0);
        List<CartLines> cartLinesList = cartLinesService.findByCart(cart);
        for (CartLines cartLines: cartLinesList) {
             cartTotal = cartTotal.add(cartLines.getCartLineProductTotal());

        }
        cart.setGrandTotal(cartTotal);

        return cart;
    }

    @Override
    public Cart findByCartLinesList(Cart cart) {
        return cartRepository.findByCartLinesList(cart);
    }
}
