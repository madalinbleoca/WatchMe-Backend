package com.dandelyon.watchMeBackend.service.implementation;

import com.dandelyon.watchMeBackend.exception.ResourceNotFoundException;
import com.dandelyon.watchMeBackend.model.Cart;
import com.dandelyon.watchMeBackend.model.User;
import com.dandelyon.watchMeBackend.repository.CartRepository;
import com.dandelyon.watchMeBackend.repository.UserRepository;
import com.dandelyon.watchMeBackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartImpl implements CartService {

    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    @Autowired
    public CartImpl(UserRepository userRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public Long findCartIdByUserId(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Cart> shoppingCarts = user.getShoppingCarts();
            if (!shoppingCarts.isEmpty()) {
                return shoppingCarts.get(0).getId();
            }
        }
        throw new ResourceNotFoundException("Cart not found for user ID: " + id);
    }
}
