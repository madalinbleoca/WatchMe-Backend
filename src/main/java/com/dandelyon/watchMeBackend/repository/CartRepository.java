package com.dandelyon.watchMeBackend.repository;

import com.dandelyon.watchMeBackend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Long findCartIdByUserId(Long Id);
}
