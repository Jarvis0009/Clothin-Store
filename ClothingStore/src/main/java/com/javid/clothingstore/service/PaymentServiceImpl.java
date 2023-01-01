package com.javid.clothingstore.service;

import com.javid.clothingstore.model.Payment;
import com.javid.clothingstore.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }
}
