package com.dandelyon.watchMeBackend.service.implementation;

import com.dandelyon.watchMeBackend.exception.ResourceNotFoundException;
import com.dandelyon.watchMeBackend.model.Cart;
import com.dandelyon.watchMeBackend.model.CartItem;
import com.dandelyon.watchMeBackend.model.Order;
import com.dandelyon.watchMeBackend.model.OrderItem;
import com.dandelyon.watchMeBackend.repository.CartItemRepository;
import com.dandelyon.watchMeBackend.repository.OrderRepository;
import com.dandelyon.watchMeBackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final CartItemRepository cartItemRepository;


    @Autowired
    public OrderImpl(OrderRepository orderRepository, CartItemRepository cartItemRepository) {
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void createOrder(Order order, Cart cart) {
        List<CartItem> cartItems = cartItemRepository.getCartItemsByCart_Id(cart.getId());
        if (cartItems == null || cartItems.isEmpty()) {
            throw new ResourceNotFoundException("The cart is empty. Cannot create an order.");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);

        // Calculate the total price of the order
        double total = calculateTotalPrice(order);

        // Set the total price in the order
        order.setTotalPrice(total);

        // Save the order to the database
        orderRepository.save(order);

        // Return the total price of the order
    }

    @Override
    public double calculateTotalPrice(Order price) {
        double totalPrice = 0.0;
        List<OrderItem> orderItems = price.getOrderItems();
        if (orderItems != null) {
            for (OrderItem orderItem : orderItems) {
                // Calculate the subtotal for each order item (product price * quantity)
                double subtotal = orderItem.getProduct().getPrice() * orderItem.getQuantity();
                totalPrice += subtotal;
            }
        }
        return totalPrice;
    }
}
