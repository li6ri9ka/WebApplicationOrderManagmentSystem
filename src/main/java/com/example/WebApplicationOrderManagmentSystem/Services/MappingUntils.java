package com.example.WebApplicationOrderManagmentSystem.Services;


import com.example.WebApplicationOrderManagmentSystem.DTO.CustomerDTO;
import com.example.WebApplicationOrderManagmentSystem.DTO.OrderDTO;
import com.example.WebApplicationOrderManagmentSystem.DTO.ProductDTO;
import com.example.WebApplicationOrderManagmentSystem.Model.Customer;
import com.example.WebApplicationOrderManagmentSystem.Model.Orders;
import com.example.WebApplicationOrderManagmentSystem.Model.Product;
import org.springframework.stereotype.Service;

@Service
public class MappingUntils {
    public ProductDTO mappingProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName_product(product.getName_product());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        return productDTO;
    }

    public CustomerDTO mappingCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName_customer(customer.getName_customer());
        customerDTO.setLogin_customer(customer.getLogin_customer());
        customerDTO.setPassword_customer(customer.getPassword_customer());
        return customerDTO;
    }

    public OrderDTO mappingOrderDTO(Orders orders) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orders.getId());
        orderDTO.setQuantity(orders.getQuantity());
        orderDTO.setStatus(orders.getStatus());
        orderDTO.setTotal_cost(orders.getTotal_cost());
        orderDTO.setOrderCreated(orders.getOrderCreated());
        orderDTO.setCustomer(orders.getCustomer());
        orderDTO.setProduct(orders.getProduct());
        return orderDTO;
    }

    public Customer mappingCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName_customer(customerDTO.getName_customer());
        customer.setLogin_customer(customerDTO.getLogin_customer());
        customer.setPassword_customer(customerDTO.getPassword_customer());
        return customer;
    }

    public Product mappingProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName_product(productDTO.getName_product());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        return product;
    }

    public Orders mappingOrders(OrderDTO orderDTO) {
        Orders orders = new Orders();
        orders.setId(orderDTO.getId());
        orders.setQuantity(orderDTO.getQuantity());
        orders.setStatus(orderDTO.getStatus());
        orders.setTotal_cost(orderDTO.getTotal_cost());
        orders.setOrderCreated(orderDTO.getOrderCreated());
        orders.setCustomer(orderDTO.getCustomer());
        orders.setProduct(orderDTO.getProduct());
        return orders;
    }

}
