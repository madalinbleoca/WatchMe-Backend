package com.dandelyon.watchMeBackend.service;

import com.dandelyon.watchMeBackend.model.Cart;
import com.dandelyon.watchMeBackend.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> getAllOrders();

    Optional<Order> getOrderById(Long id);

    void createOrder(Order order, Cart cart);

    double calculateTotalPrice(Order price);


}
