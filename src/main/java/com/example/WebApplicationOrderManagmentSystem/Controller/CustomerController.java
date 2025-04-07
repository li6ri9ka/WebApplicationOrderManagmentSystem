package com.example.WebApplicationOrderManagmentSystem.Controller;

import com.example.WebApplicationOrderManagmentSystem.DTO.CustomerDTO;
import com.example.WebApplicationOrderManagmentSystem.Model.Customer;
import com.example.WebApplicationOrderManagmentSystem.Repositories.CustomerRepository;
import com.example.WebApplicationOrderManagmentSystem.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<CustomerDTO> getAllCustomer() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @GetMapping("update/{id}")
    public ResponseEntity<Customer> getCustomerForUpdate(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        Customer customerToUpdate = customerService.updateCustomer(id, customer);
        if (customerToUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerToUpdate);
    }


    @GetMapping("/delete/{id}")
    public Object getCustomerForDelete(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteCustomer(@PathVariable Long id){
        if (customerService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
