package com.javid.clothingstore.repository;

import com.javid.clothingstore.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment save(Payment payment);

}
