package com.example.WebApplicationOrderManagmentSystem.Repositories;

import com.example.WebApplicationOrderManagmentSystem.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCustomer_Id(Long id_customer);
    List<Orders> findByStatusOrder(String statusOrder);


}