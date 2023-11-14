package com.dandelyon.watchMeBackend.repository;

import com.dandelyon.watchMeBackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


}
