package com.example.WebApplicationOrderManagmentSystem.Model;

import jakarta.persistence.*;

@Entity
public class AccountUser {
    @Id
    @Column(name = "id_accountUser")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "login_user")
    private String login;
    @Column(name = "password_user")
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id_role_user")
    private RoleUser role;
    @OneToOne(mappedBy = "accountUser")
    private Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
