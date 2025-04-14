package com.example.WebApplicationOrderManagmentSystem.Repositories;

import com.example.WebApplicationOrderManagmentSystem.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
