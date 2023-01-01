package com.javid.clothingstore.service;

import com.javid.clothingstore.model.Cart;
import com.javid.clothingstore.model.CartLines;
import com.javid.clothingstore.model.Product;
import com.javid.clothingstore.model.User;
import com.javid.clothingstore.repository.CartLinesRepository;
import com.javid.clothingstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CartLinesServiceImpl implements CartLinesService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartLinesRepository cartLinesRepository;

    @Override
    public CartLines findById(Long id) {
        return cartLinesRepository.findById(id).get();
    }

    @Override
    public CartLines saveCartLines(CartLines cartLines) {
        return cartLinesRepository.saveAndFlush(cartLines);
    }

    @Override
    public CartLines updateCartLines(CartLines cartLines) {
        BigDecimal bigDecimal = new BigDecimal(cartLines.getProduct().getProductPrice()).multiply(new BigDecimal(cartLines.getCartLineProductQuantity()));
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        cartLines.setCartLineProductTotal(bigDecimal);
        cartLinesRepository.save(cartLines);
        return cartLines;
    }

    @Override
    public boolean removeCartLines(CartLines cartLines) {
       cartLinesRepository.delete(cartLines);
       return false;

    }

    @Override
    public void deleteById(Long id) {
         cartLinesRepository.deleteById(id);
    }


    @Override
    public CartLines addProductToCartLines(Product product, User user, int quantity) {
    List<CartLines> cartLinesList = findByCart(user.getCart());
        for (CartLines cartLines: cartLinesList ) {
            if(product.getId() == cartLines.getProduct().getId()) {
                cartLines.setCartLineProductQuantity(cartLines.getCartLineProductQuantity() + quantity);
                cartLines.setCartLineProductTotal(new BigDecimal(product.getProductPrice()).multiply(new BigDecimal(quantity)));
                cartLinesRepository.save(cartLines);
                return cartLines;
            }
        }
        CartLines cartLines = new CartLines();
        cartLines.setCart(user.getCart());
        cartLines.setProduct(product);
        cartLines.setCartLineProductQuantity(quantity);
        cartLines.setCartLineProductTotal(new BigDecimal(product.getProductPrice()).multiply(new BigDecimal(quantity)));
        cartLinesRepository.save(cartLines);


        return cartLines;
    }

    @Override
    public List<CartLines> findByCart(Cart cart) {
        return cartLinesRepository.findByCart(cart);
    }


}
