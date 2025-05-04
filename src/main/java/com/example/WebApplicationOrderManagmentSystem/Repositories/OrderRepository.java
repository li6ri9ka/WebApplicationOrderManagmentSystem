package com.example.WebApplicationOrderManagmentSystem.Repositories;

import com.example.WebApplicationOrderManagmentSystem.Model.Orders;
import com.example.WebApplicationOrderManagmentSystem.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByAccountUser_Id(Long id_account_user);
    List<Orders> findByStatus(String statusOrder);
    public interface ProductRepository extends JpaRepository<Product, Long> {
        @Modifying
        @Query("UPDATE Product p SET p.quantity = p.quantity - :amount WHERE p.id_product = :productId")
        void decreaseQuantity(@Param("productId") Long productId,
                              @Param("amount") int amount);
    }


}