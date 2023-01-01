package com.javid.clothingstore.model;

import javax.persistence.*;

@Entity
public class BillingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "billingAddressFirstName")
    private String billingAddressFirstName;

    @Column(name = "billingAddressLastName")
    private String billingAddressLastName;
    @Column(name = "billingAddressStreet")
    private String billingAddressStreet;
    @Column(name = "mobileNumber")
    private String mobileNumber;
    @Column(name = "billingAddressCity")
    private String billingAddressCity;
    @Column(name = "billingAddressState")
    private String billingAddressState;
    @Column(name = "billingAddressCountry")
    private String billingAddressCountry;
    @Column(name = "billingAddressZipcode")
    private String billingAddressZipcode;

    @OneToOne
    private Order order;

    public String getBillingAddressLastName() {
        return billingAddressLastName;
    }

    public void setBillingAddressLastName(String billingAddressLastName) {
        this.billingAddressLastName = billingAddressLastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillingAddressFirstName() {
        return billingAddressFirstName;
    }

    public void setBillingAddressFirstName(String billingAddressFirstName) {
        this.billingAddressFirstName = billingAddressFirstName;
    }


    public String getBillingAddressStreet() {
        return billingAddressStreet;
    }

    public void setBillingAddressStreet(String billingAddressStreet) {
        this.billingAddressStreet = billingAddressStreet;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getBillingAddressCity() {
        return billingAddressCity;
    }

    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
    }

    public String getBillingAddressState() {
        return billingAddressState;
    }

    public void setBillingAddressState(String billingAddressState) {
        this.billingAddressState = billingAddressState;
    }

    public String getBillingAddressCountry() {
        return billingAddressCountry;
    }

    public void setBillingAddressCountry(String billingAddressCountry) {
        this.billingAddressCountry = billingAddressCountry;
    }

    public String getBillingAddressZipcode() {
        return billingAddressZipcode;
    }

    public void setBillingAddressZipcode(String billingAddressZipcode) {
        this.billingAddressZipcode = billingAddressZipcode;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
