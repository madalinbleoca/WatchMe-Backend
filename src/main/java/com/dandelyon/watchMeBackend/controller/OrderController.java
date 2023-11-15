package com.dandelyon.watchMeBackend.controller;

import com.dandelyon.watchMeBackend.model.Cart;
import com.dandelyon.watchMeBackend.model.Order;
import com.dandelyon.watchMeBackend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String listOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order/list";
    }

    @GetMapping("/{orderId}")
    public String viewOrder(@PathVariable Long orderId, Model model) {
        Optional<Order> order = orderService.getOrderById(orderId);
        model.addAttribute("order", order);
        return "order/view";
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute Order order, Cart cart) {
        orderService.createOrder(order, cart);
        return "redirect:/orders";
    }
}
