package com.example.demo.repo;

import com.example.demo.dto.CustomerDTO;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CustomerRepository {
    private final Map<Long, CustomerDTO> map = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public CustomerDTO save(CustomerDTO c) {
        if (c.getId() == null) c.setId(idGenerator.incrementAndGet());
        map.put(c.getId(), c);
        return c;
    }
    public Optional<CustomerDTO> findById(Long id) {
        return Optional.ofNullable(map.get(id));
    }
    public List<CustomerDTO> findAll() {
        return new ArrayList<>(map.values());
    }
    public void deleteById(Long id) {
        map.remove(id);
    }
}
