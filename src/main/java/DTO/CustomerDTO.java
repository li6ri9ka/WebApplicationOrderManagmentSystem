package DTO;

import Model.Orders;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
public class CustomerDTO {
     Long id;
     String name_customer;
     String login_customer;
     String password_customer;
}
