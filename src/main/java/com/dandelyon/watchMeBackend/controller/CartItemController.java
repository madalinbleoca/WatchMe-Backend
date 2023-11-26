package com.dandelyon.watchMeBackend.controller;

import com.dandelyon.watchMeBackend.model.CartItem;
import com.dandelyon.watchMeBackend.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartItemController {

    private final CartItemService cartItemService;

    @GetMapping
    public String viewCart(Model model) {
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        model.addAttribute("cartItems", cartItems);
        return "cart/view";
    }

    @PostMapping("/add")
    public String addToCart(@ModelAttribute CartItem cartItem) {
        cartItemService.addToCart(cartItem);
        return "redirect:/cart";
    }

    @DeleteMapping("/remove/{cartItemId}")
    public String removeFromCart(@PathVariable Long cartItemId) {
        cartItemService.removeFromCart(cartItemId);
        return "redirect:/cart";
    }
}
