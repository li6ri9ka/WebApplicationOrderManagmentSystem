package com.example.WebApplicationOrderManagmentSystem.Repositories;

import com.example.WebApplicationOrderManagmentSystem.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
