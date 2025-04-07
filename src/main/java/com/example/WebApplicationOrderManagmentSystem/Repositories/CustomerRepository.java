package com.example.WebApplicationOrderManagmentSystem.Repositories;

import com.example.WebApplicationOrderManagmentSystem.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
