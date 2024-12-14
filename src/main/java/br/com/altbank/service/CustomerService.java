package br.com.altbank.service;

import br.com.altbank.dto.CustomerDTO;
import br.com.altbank.entity.Address;
import br.com.altbank.entity.Customer;
import br.com.altbank.repository.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class CustomerService {
    @Inject
    CustomerRepository customerRepository;

    public Customer createCustomer(CustomerDTO customerDTO) {

        Address address = new Address(customerDTO.getDeliveryAddress().getStreet(), customerDTO.getDeliveryAddress().getCity(), customerDTO.getDeliveryAddress().getState(), customerDTO.getDeliveryAddress().getZipCode(), customerDTO.getDeliveryAddress().getCountry());

        Customer customer = new Customer(customerDTO.getName(), customerDTO.getDocument(), address);

        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.delete(id);
    }

    public Customer findByDocument(String document) {
        Optional<Customer> optionalCustomer = customerRepository.findByDocument(document);
        return optionalCustomer.orElseThrow(() -> new RuntimeException("Customer not found"));
    }

}
