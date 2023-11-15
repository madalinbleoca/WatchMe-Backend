package com.dandelyon.watchMeBackend.repository;

import com.dandelyon.watchMeBackend.model.CartItem;
import com.dandelyon.watchMeBackend.model.Product;
import com.dandelyon.watchMeBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> getCartItemsByCart_Id(Long id);

    CartItem findByProductAndUser(Product product, User user);

}
