package com.dandelyon.watchMeBackend.service.implementation;

import com.dandelyon.watchMeBackend.model.Product;
import com.dandelyon.watchMeBackend.repository.ProductRepository;
import com.dandelyon.watchMeBackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with id " + id +
                        " doesn't exist in DB"));
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findProductsByName(name);
    }
}
