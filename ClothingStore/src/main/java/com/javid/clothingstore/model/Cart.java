package com.javid.clothingstore.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Column(name = "grandTotal")
    private BigDecimal grandTotal;

    @OneToMany
    private List<CartLines> cartLinesList;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public List<CartLines> getCartLinesList() {
        return cartLinesList;
    }

    public void setCartLinesList(List<CartLines> cartLinesList) {
        this.cartLinesList = cartLinesList;
    }
}
