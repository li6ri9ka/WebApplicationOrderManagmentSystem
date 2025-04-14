package com.example.WebApplicationOrderManagmentSystem.Services;

import com.example.WebApplicationOrderManagmentSystem.DTO.ProductDTO;
import com.example.WebApplicationOrderManagmentSystem.Model.Product;
import com.example.WebApplicationOrderManagmentSystem.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MappingUntils mappingUntils;

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(mappingUntils::mappingProductDTO).toList();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        if(productRepository.existsById(id)) {
            product.setId_product(id);
            return productRepository.save(product);
        }
        else{
            throw new RuntimeException("Product not found");
        }
    }

    public void deleteProduct(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("Product not found");
        }
    }


}
