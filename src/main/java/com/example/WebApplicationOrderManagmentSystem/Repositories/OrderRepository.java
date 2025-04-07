package com.example.WebApplicationOrderManagmentSystem.Repositories;

import com.example.WebApplicationOrderManagmentSystem.Model.Orders;
import com.example.WebApplicationOrderManagmentSystem.Model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCustomerId(Long customer_id);
    List<Orders> findByStatus(Status status);
}
