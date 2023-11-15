package com.dandelyon.watchMeBackend.service;

import com.dandelyon.watchMeBackend.model.Product;

import java.util.List;

public interface ProductService {

   List<Product> getAllProducts();

   Product getProductById(Long id);

   Product getProductByName(String name);

   Product createProduct(Product product);

   Product updateProduct(Product product);

   void deleteProduct(Long productId);
}
