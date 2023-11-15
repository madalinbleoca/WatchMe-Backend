package com.dandelyon.watchMeBackend.service.implementation;

import com.dandelyon.watchMeBackend.exception.ResourceNotFoundException;
import com.dandelyon.watchMeBackend.model.CartItem;
import com.dandelyon.watchMeBackend.repository.CartItemRepository;
import com.dandelyon.watchMeBackend.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }


    @Override
    public void addToCart(CartItem cartItem) {
        CartItem existingCartItem = cartItemRepository.findByProductAndUser(cartItem.getProduct(),
                cartItem.getUser());

        if (existingCartItem != null) {
            int currentQuantity = existingCartItem.getQuantity();
            // Save the updated cart item to the database.
            cartItemRepository.save(existingCartItem);
        } else {
            cartItemRepository.save(cartItem);
        }
    }

    @Override
    public void removeFromCart(Long cartItemId) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            cartItemRepository.delete(cartItem);
        } else {
            throw new ResourceNotFoundException("Cart item with ID " + cartItemId + " not found.");
        }
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public List<CartItem> getCartItemsByCartId(Long cartId) {
        return cartItemRepository.getCartItemsByCart_Id(cartId);
    }
}
