package DTO;

import Model.Customer;
import Model.Product;
import Model.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderDTO {
    Long id;
    int quantity;
    Status status;
    double total_cost;
    Timestamp orderCreated;
    Customer customer;
    Product product;
}
