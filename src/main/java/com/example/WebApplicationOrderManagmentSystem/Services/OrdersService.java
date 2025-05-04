package com.example.WebApplicationOrderManagmentSystem.Services;

import com.example.WebApplicationOrderManagmentSystem.DTO.OrderDTO;
import com.example.WebApplicationOrderManagmentSystem.DTO.OrderItemDTO;
import com.example.WebApplicationOrderManagmentSystem.DTO.OrderReqDTO;
import com.example.WebApplicationOrderManagmentSystem.Model.AccountUser;
import com.example.WebApplicationOrderManagmentSystem.Model.OrderItem;
import com.example.WebApplicationOrderManagmentSystem.Model.Orders;
import com.example.WebApplicationOrderManagmentSystem.Model.Product;
import com.example.WebApplicationOrderManagmentSystem.Repositories.OrderProductRepository;
import com.example.WebApplicationOrderManagmentSystem.Repositories.OrderRepository;
import com.example.WebApplicationOrderManagmentSystem.Repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MappingUntils mappingUntils;
    @Autowired
    private AccountUserService accountUserService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;




    public List<OrderDTO> findAll(){
        return orderRepository.findAll().stream().map(mappingUntils::mappingOrderDTO).toList();
    }

    public Orders findById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public Orders addOrder(Orders order){
        return orderRepository.save(order);
    }

    public List<OrderDTO> findByUserId(Long userId) {
        return orderRepository.findByAccountUser_Id(userId)
                .stream()
                .map(mappingUntils::mappingOrderDTO)
                .toList();
    }

    public Orders updateOrder(Long id,Orders order){
        if(orderRepository.existsById(id)){
            order.setId(id);
            return orderRepository.save(order);
        }
        else {
            throw new RuntimeException("Order not found");
        }
    }

    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order not found");
        }
    }


    @Transactional
    public Orders createOrder(Long userId, OrderReqDTO orderRequest) {
        AccountUser user = accountUserService.findById(userId);
        Orders order = new Orders();
        order.setAccountUser(user);
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());

        for (OrderItemDTO itemDto : orderRequest.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Товар не найден"));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDto.getQuantity());
            order.addItem(orderItem);
        }

        order.setTotalCost(orderRequest.getTotalPrice());
        return orderRepository.save(order);
    }
}
