package com.example.tpproducts.service;

import com.example.tpproducts.dto.CustomerRequestDto;
import com.example.tpproducts.dto.CustomerResponseDto;
import com.example.tpproducts.model.Customer;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CustomerService {

    private final Map<Long, Customer> customerRepository = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public CustomerResponseDto createCustomer(CustomerRequestDto requestDto) {
        Long id = idGenerator.getAndIncrement();
        Customer customer = new Customer(
                id,
                requestDto.getFirstName(),
                requestDto.getLastName(),
                requestDto.getEmail(),
                requestDto.getPhone()
        );
        customerRepository.put(id, customer);
        return mapToResponseDto(customer);
    }

    public Optional<CustomerResponseDto> getCustomerById(Long id) {
        Customer customer = customerRepository.get(id);
        return customer != null ? Optional.of(mapToResponseDto(customer)) : Optional.empty();
    }

    private CustomerResponseDto mapToResponseDto(Customer customer) {
        return new CustomerResponseDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }
}