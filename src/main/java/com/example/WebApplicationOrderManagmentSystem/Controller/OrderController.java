package com.example.WebApplicationOrderManagmentSystem.Controller;


import com.example.WebApplicationOrderManagmentSystem.DTO.OrderDTO;
import com.example.WebApplicationOrderManagmentSystem.DTO.OrderReqDTO;
import com.example.WebApplicationOrderManagmentSystem.JwtUtils;
import com.example.WebApplicationOrderManagmentSystem.Model.Orders;
import com.example.WebApplicationOrderManagmentSystem.Services.OrdersService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    private final JwtUtils jwtTokenUtil;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public OrderController(JwtUtils jwtUtil) {
        this.jwtTokenUtil = jwtUtil;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderDTO> getAllOrders() {
        return ordersService.findAll();
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<OrderDTO>> getUserOrders(@PathVariable Long userId) {
        List<OrderDTO> orders = ordersService.findByUserId(userId);
        List<OrderDTO> dtos = orders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Orders order = ordersService.findById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping("/newOrder")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createOrder(
            @Valid @RequestBody OrderReqDTO orderRequest,
            @RequestHeader("Authorization") String token,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(bindingResult.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .collect(Collectors.joining(", ")));
        }

        try {
            Long userId = jwtTokenUtil.extractUserId(token.replace("Bearer ", ""));
            Orders createdOrder = ordersService.createOrder(userId, orderRequest);
            return ResponseEntity.ok(createdOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/updateOrder/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Orders> getOrderForUpdate(@PathVariable Long id) {
        Orders order = ordersService.findById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PutMapping("/updateOrder/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders order) {
        Orders orders = ordersService.updateOrder(id,order);
        if (orders == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/deleteOrder/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Object getOrderForDelete(@PathVariable Long id) {
        Orders order = ordersService.findById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/deleteOrder/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Object deleteOrder(@PathVariable Long id) {
        if (ordersService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        ordersService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }



}
