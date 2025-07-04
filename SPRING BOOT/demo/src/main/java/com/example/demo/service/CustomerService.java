package com.example.demo.service;

import com.example.demo.dto.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final List<CustomerDTO> customers = new ArrayList<>();
    private long nextId = 1;

    public List<CustomerDTO> getAllCustomers() {
        return customers;
    }

    public CustomerDTO getCustomerById(Long id) {
        Optional<CustomerDTO> customer = customers.stream().filter(c -> c.getId().equals(id)).findFirst();
        return customer.orElse(null);
    }

    public CustomerDTO createCustomer(CustomerDTO customer) {
        customer.setId(nextId++);
        customers.add(customer);
        return customer;
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customer) {
        CustomerDTO existingCustomer = getCustomerById(id);
        if (existingCustomer != null) {
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
        }
        return existingCustomer;
    }

    public boolean deleteCustomer(Long id) {
        return customers.removeIf(c -> c.getId().equals(id));
    }
}
