package com.javid.clothingstore.service;

import com.javid.clothingstore.model.*;

public interface OrderService {

    Order saveOrder(Order order);
    Order findOne(Long id);

}
