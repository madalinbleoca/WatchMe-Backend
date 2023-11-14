package com.dandelyon.watchMeBackend.repository;

import com.dandelyon.watchMeBackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductsByName(String name);

}
