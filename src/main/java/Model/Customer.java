package Model;


import jakarta.persistence.*;

import java.util.List;
import Model.Orders;
import lombok.Data;

@Data
@Entity
public class Customer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name_customer;
    @Column
    private String login_customer;
    @Column
    private String password_customer;
    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;
}
