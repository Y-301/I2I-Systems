package com.example.demo.service;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.repo.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class CustomerService {
    private final CustomerRepository repo;
    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }
    public CustomerDTO create(CustomerDTO c) {
        return repo.save(c);
    }
    public CustomerDTO getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }
    public List<CustomerDTO> getAll() {
        return repo.findAll();
    }
    public CustomerDTO update(Long id, CustomerDTO c) {
        CustomerDTO existing = getById(id);
        existing.setName(c.getName());
        existing.setEmail(c.getEmail());
        return repo.save(existing);
    }
    public void delete(Long id) {
        getById(id); // ensure exists
        repo.deleteById(id);
    }
}
