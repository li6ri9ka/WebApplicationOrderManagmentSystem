package com.example.WebApplicationOrderManagmentSystem.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

import lombok.Data;

@Data
@Entity
public class Customer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_customer;
    @Column
    private String name_customer;
    @Column
    private String login_customer;
    @Column
    private String password_customer;
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Orders> orders;

    public Long getId() {
        return id_customer;
    }

    public void setId(Long id) {
        this.id_customer = id;
    }

    public String getName_customer() {
        return name_customer;
    }

    public void setName_customer(String name_customer) {
        this.name_customer = name_customer;
    }

    public String getLogin_customer() {
        return login_customer;
    }

    public void setLogin_customer(String login_customer) {
        this.login_customer = login_customer;
    }

    public String getPassword_customer() {
        return password_customer;
    }

    public void setPassword_customer(String password_customer) {
        this.password_customer = password_customer;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
