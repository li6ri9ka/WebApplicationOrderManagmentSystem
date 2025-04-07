package com.example.WebApplicationOrderManagmentSystem.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_order;
    @Column
    private String name_product;
    @Column
    private double price;
    @Column
    private int quantity;
    @OneToMany(mappedBy = "product")
    private List<Orders> orders;


    public Long getId() {
        return id_order;
    }

    public void setId(Long id) {
        this.id_order = id;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
