package com.dandelyon.watchMeBackend.service.implementation;

import com.dandelyon.watchMeBackend.exception.ResourceNotFoundException;
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

    @Override
    public void createProduct(Product product) {
        Product existingProduct = productRepository.findProductsByName(product.getName());
        if (existingProduct == null) {
            productRepository.save(product);
        } else {
            throw new ResourceNotFoundException("Product with this name is not unique.");
        }
    }

    @Override
    public void updateProduct(Product product) {
        Product existingProduct = getProductById(product.getId());

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setStockQuantity(product.getStockQuantity());

        productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            productRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Product with ID " + id + " not found in DB.");
        }
    }
}
