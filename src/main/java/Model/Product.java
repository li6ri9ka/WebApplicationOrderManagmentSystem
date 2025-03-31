package Model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name_product;
    @Column
    private double price;
    @Column
    private int quantity;
    @OneToMany(mappedBy = "product")
    private List<Orders> orders;

}
