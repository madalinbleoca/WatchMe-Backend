package com.dandelyon.watchMeBackend.service;

import com.dandelyon.watchMeBackend.model.CartItem;

import java.util.List;

public interface CartItemService {

    void addToCart(CartItem cartItem);

    void removeFromCart(Long cartItemId);

    List<CartItem> getAllCartItems();

    List<CartItem> getCartItemsByCartId(Long cartId);
}
