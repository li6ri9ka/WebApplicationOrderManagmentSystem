package Services;

import DTO.CustomerDTO;
import Model.Customer;
import Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MappingUntils mappingUntils;

    public List<CustomerDTO> findAll() {
        return customerRepository.findAll().stream().map(mappingUntils::mappingCustomerDTO).toList();
    }
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    public Customer updateCustomer(String name, String login, Long id, Customer customer) {
        if (customerRepository.existsById(id)) {
            customer.setName_customer(name);
            customer.setLogin_customer(login);
            return customerRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }
    public void deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Customer not found");
        }
    }

}
