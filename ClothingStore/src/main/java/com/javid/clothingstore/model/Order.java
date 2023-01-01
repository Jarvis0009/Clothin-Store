package com.javid.clothingstore.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "orderDate")
    private Date orderDate = new Date(System.currentTimeMillis());
    @Column(name = "orderStatus")
    private String orderStatus;
    @OneToOne(cascade = CascadeType.ALL)
    private BillingAddress billingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<CartLines> cartLinesList;

    @Column(name = "orderTotal")
    private BigDecimal orderTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public List<CartLines> getCartLinesList() {
        return cartLinesList;
    }

    public void setCartLinesList(List<CartLines> cartLinesList) {
        this.cartLinesList = cartLinesList;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }
}
