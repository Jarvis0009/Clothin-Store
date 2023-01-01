package com.javid.clothingstore.service;

import com.javid.clothingstore.model.*;
import com.javid.clothingstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartLinesService cartLinesService;


    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findOne(Long id) {
        return orderRepository.findById(id).get();
    }
}
