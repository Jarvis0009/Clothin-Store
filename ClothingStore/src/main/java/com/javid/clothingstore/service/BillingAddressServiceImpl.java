package com.javid.clothingstore.service;

import com.javid.clothingstore.model.BillingAddress;
import com.javid.clothingstore.repository.BillingAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingAddressServiceImpl implements BillingAddressService {

    @Autowired
    private BillingAddressRepository billingAddressRepository;

    @Override
    public BillingAddress saveBillingAddress(BillingAddress billingAddress) {
        return billingAddressRepository.save(billingAddress);
    }
}
