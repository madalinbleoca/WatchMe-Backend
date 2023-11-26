package com.dandelyon.watchMeBackend.controller;

import com.dandelyon.watchMeBackend.model.Product;
import com.dandelyon.watchMeBackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product productFromDB = productService.getProductById(id);

        return new ResponseEntity<>(productFromDB, HttpStatus.OK);
    }

   @PostMapping("/addNewProduct")
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
        Product newProduct = productService.createProduct(product);

        return new ResponseEntity<>(newProduct, HttpStatus.OK);
   }

   @PostMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
   }

   @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        Product productFromDB = productService.getProductById(id);
        productService.deleteProduct(id);

        return new ResponseEntity<>("Product with id " + id + " was deleted from DB", HttpStatus.OK);
   }
}
