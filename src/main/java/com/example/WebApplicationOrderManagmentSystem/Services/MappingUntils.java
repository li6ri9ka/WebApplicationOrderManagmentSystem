package com.example.WebApplicationOrderManagmentSystem.Services;


import com.example.WebApplicationOrderManagmentSystem.DTO.OrderDTO;
import com.example.WebApplicationOrderManagmentSystem.DTO.ProductDTO;
import com.example.WebApplicationOrderManagmentSystem.DTO.SingUpDTO;
import com.example.WebApplicationOrderManagmentSystem.DTO.UserDTO;
import com.example.WebApplicationOrderManagmentSystem.Model.AccountUser;
import com.example.WebApplicationOrderManagmentSystem.Model.Orders;
import com.example.WebApplicationOrderManagmentSystem.Model.Product;
import org.springframework.stereotype.Service;

@Service
public class MappingUntils {
    public ProductDTO mappingProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId_product());
        productDTO.setName_product(product.getName_product());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        return productDTO;
    }



    public OrderDTO mappingOrderDTO(Orders orders) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orders.getId());
        orderDTO.setStatus_order(orders.getStatus());
        orderDTO.setTotal_cost(orders.getTotalCost());
        orderDTO.setOrderCreated(orders.getCreatedAt());
        orderDTO.setAccountUser(orders.getAccountUser());
        return orderDTO;
    }

    public UserDTO mappingAccountUserDTO(AccountUser accountUser) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(accountUser.getId());
        userDTO.setEmail(accountUser.getEmail());
        userDTO.setLogin(accountUser.getLogin());
        return userDTO;
    }


    public Product mappingProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId_product(productDTO.getId());
        product.setName_product(productDTO.getName_product());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        return product;
    }

}
