package com.example.WebApplicationOrderManagmentSystem.DTO;


import lombok.Data;

@Data
public class ProductDTO {
     Long id;
     String name_product;
     double price;
     int quantity;

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
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
}
