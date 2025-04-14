package com.example.WebApplicationOrderManagmentSystem.Controller;


import com.example.WebApplicationOrderManagmentSystem.DTO.OrderDTO;
import com.example.WebApplicationOrderManagmentSystem.Model.Orders;
import com.example.WebApplicationOrderManagmentSystem.Repositories.OrderRepository;
import com.example.WebApplicationOrderManagmentSystem.Services.OrdersService;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return ordersService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Orders order = ordersService.findById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping("/newOrder")
    public Orders createOrder(@RequestBody Orders order) {
        return ordersService.addOrder(order);
    }


    @GetMapping("/updateOrder/{id}")
    public ResponseEntity<Orders> getOrderForUpdate(@PathVariable Long id) {
        Orders order = ordersService.findById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders order) {
        Orders orders = ordersService.updateOrder(id,order);
        if (orders == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/deleteOrder/{id}")
    public Object getOrderForDelete(@PathVariable Long id) {
        Orders order = ordersService.findById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/deleteOrder/{id}")
    public Object deleteOrder(@PathVariable Long id) {
        if (ordersService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        ordersService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }



}
