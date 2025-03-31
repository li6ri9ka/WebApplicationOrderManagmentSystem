package DTO;


import Model.Orders;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
     Long id;
     String name_product;
     double price;
     int quantity;
}
