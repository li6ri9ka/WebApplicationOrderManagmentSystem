package com.example.WebApplicationOrderManagmentSystem.DTO;

import lombok.Data;


@Data
public class CustomerDTO {
     Long id;
     String name_customer;
     String login_customer;
     String password_customer;

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
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
}
