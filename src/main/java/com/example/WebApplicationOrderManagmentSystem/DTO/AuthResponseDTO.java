package com.example.WebApplicationOrderManagmentSystem.DTO;

import com.example.WebApplicationOrderManagmentSystem.Model.RoleUser;

public class AuthResponseDTO {
    private RoleUser role;
    private String login;
    private String token;

    public AuthResponseDTO(RoleUser role, String login, String token) {
        this.role = role;
        this.login = login;
        this.token = token;
    }

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
