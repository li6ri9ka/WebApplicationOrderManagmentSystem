package com.example.WebApplicationOrderManagmentSystem.DTO;

import com.example.WebApplicationOrderManagmentSystem.Model.AccountUser;
import lombok.Data;


@Data
public class CustomerDTO {
     Long id;
     String name_customer;
     AccountUser accountUser;

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

     public AccountUser getAccountUser() {
          return accountUser;
     }

     public void setAccountUser(AccountUser accountUser) {
          this.accountUser = accountUser;
     }
}
