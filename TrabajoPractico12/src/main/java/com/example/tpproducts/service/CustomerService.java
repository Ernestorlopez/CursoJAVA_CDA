package com.example.tpproducts.service;

import com.example.tpproducts.dto.CustomerRequestDto;
import com.example.tpproducts.dto.CustomerResponseDto;
import com.example.tpproducts.model.Customer;
import com.example.tpproducts.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con id: " + id));
        return toResponseDto(customer);
    }

    public CustomerResponseDto createCustomer(CustomerRequestDto requestDto) {
        Customer customer = new Customer();
        customer.setFirstName(requestDto.getFirstName());
        customer.setLastName(requestDto.getLastName());
        customer.setEmail(requestDto.getEmail());
        customer.setPhone(requestDto.getPhone());

        Customer savedCustomer = customerRepository.save(customer);
        return toResponseDto(savedCustomer);
    }

    private CustomerResponseDto toResponseDto(Customer customer) {
        return new CustomerResponseDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }
}