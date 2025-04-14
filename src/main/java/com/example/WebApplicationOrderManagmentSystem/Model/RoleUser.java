package com.example.WebApplicationOrderManagmentSystem.Model;

import jakarta.persistence.*;

@Entity
public class RoleUser {
    @Id
    @Column(name = "id_role_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "role_user")
    private String role;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
