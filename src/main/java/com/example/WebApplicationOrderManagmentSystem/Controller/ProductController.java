package com.example.WebApplicationOrderManagmentSystem.Controller;


import com.example.WebApplicationOrderManagmentSystem.DTO.ProductDTO;
import com.example.WebApplicationOrderManagmentSystem.Model.Product;
import com.example.WebApplicationOrderManagmentSystem.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Product product = (Product) productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }


    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }


    @GetMapping("/update/{id}")
    public ResponseEntity<Product> getProductForUpdate(@PathVariable Long id){
        Product product = (Product) productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product productToUpdate = (Product) productService.updateProduct(id,product);
        if (productToUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productToUpdate);
    }

    @GetMapping("/delete/{id}")
    public Object getProductForDelete(@PathVariable Long id){
        Product product = (Product) productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/delete/{id}")
    public Object deleteProduct(@PathVariable Long id){
        if (productService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
