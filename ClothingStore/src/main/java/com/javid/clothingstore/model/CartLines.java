package com.javid.clothingstore.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class CartLines {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Product product;

    @Column(name = "CartLineProductName")
    private String CartLineProductName;

    @Column(name = "CartLineProductPrice")
    private int CartLineProductPrice;

    @Column(name = "CartLineProductQuantity")
    private int CartLineProductQuantity;

    @Column(name = "CartLineProductTotal")
    private BigDecimal CartLineProductTotal;

    @Column(name = "CartLineProductColor")
    private String CartLineProductColor;

    @Column(name = "CartLineProductSize")
    private String CartLineProductSize;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;


    public String getCartLineProductColor() {
        return CartLineProductColor;
    }

    public void setCartLineProductColor(String cartLineProductColor) {
        CartLineProductColor = cartLineProductColor;
    }

    public String getCartLineProductSize() {
        return CartLineProductSize;
    }

    public void setCartLineProductSize(String cartLineProductSize) {
        CartLineProductSize = cartLineProductSize;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCartLineProductName() {
        return CartLineProductName;
    }

    public void setCartLineProductName(String cartLineProductName) {
        CartLineProductName = cartLineProductName;
    }

    public int getCartLineProductPrice() {
        return CartLineProductPrice;
    }

    public void setCartLineProductPrice(int cartLineProductPrice) {
        CartLineProductPrice = cartLineProductPrice;
    }

    public int getCartLineProductQuantity() {
        return CartLineProductQuantity;
    }

    public void setCartLineProductQuantity(int cartLineProductQuantity) {
        CartLineProductQuantity = cartLineProductQuantity;
    }

    public BigDecimal getCartLineProductTotal() {
        return CartLineProductTotal;
    }

    public void setCartLineProductTotal(BigDecimal cartLineProductTotal) {
        CartLineProductTotal = cartLineProductTotal;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
