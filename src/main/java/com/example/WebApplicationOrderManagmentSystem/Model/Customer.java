package com.example.WebApplicationOrderManagmentSystem.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

import lombok.Data;

@Data
@Entity
public class Customer {
    @Id
    @Column(name = "id_customer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name_customer;
    @OneToOne
    @JoinColumn(name = "accountUser_id")
    private AccountUser accountUser;
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Orders> orders;

    public Long getIdCustomer() {
        return id;
    }

    public void setId_customer(Long id_customer) {
        this.id = id_customer;
    }

    public String getName_customer() {
        return name_customer;
    }

    public void setName_customer(String name_customer) {
        this.name_customer = name_customer;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }
}
