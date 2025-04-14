package com.example.WebApplicationOrderManagmentSystem.DTO;

import com.example.WebApplicationOrderManagmentSystem.Model.Customer;
import com.example.WebApplicationOrderManagmentSystem.Model.Product;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderDTO {
    Long id;
    int quantity;
    String status_order;
    double total_cost;
    Timestamp orderCreated;
    Customer customer;
    Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus_order() {
        return status_order;
    }

    public void setStatus_order(String status_order) {
        this.status_order = status_order;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public Timestamp getOrderCreated() {
        return orderCreated;
    }

    public void setOrderCreated(Timestamp orderCreated) {
        this.orderCreated = orderCreated;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
