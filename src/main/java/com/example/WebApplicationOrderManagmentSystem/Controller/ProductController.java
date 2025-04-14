package com.example.WebApplicationOrderManagmentSystem.Controller;


import com.example.WebApplicationOrderManagmentSystem.DTO.ProductDTO;
import com.example.WebApplicationOrderManagmentSystem.Model.Product;
import com.example.WebApplicationOrderManagmentSystem.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }


    @PostMapping("/newProduct")
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }


    @GetMapping("/updateProduct/{id}")
    public ResponseEntity<Product> getProductForUpdate(@PathVariable Long id){
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product productToUpdate = productService.updateProduct(id, product);
        if (productToUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productToUpdate);
    }

    @GetMapping("/deleteProduct/{id}")
    public Object getProductForDelete(@PathVariable Long id){
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/deleteProduct/{id}")
    public Object deleteProduct(@PathVariable Long id){
        if (productService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
