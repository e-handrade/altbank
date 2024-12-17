package br.com.altbank.service;

import br.com.altbank.dto.AddressDTO;
import br.com.altbank.dto.CustomerDTO;
import br.com.altbank.entity.Address;
import br.com.altbank.entity.Customer;
import br.com.altbank.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    CustomerRepository customerRepository;
    CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerService = new CustomerService();
        customerService.customerRepository = customerRepository;
    }

    @Test
    void testCreateCustomer() {
        Address address = new Address("Rua Teste", "Cidade Teste", "Estado Teste", "12345-678", "Brasil");
        Customer mockCustomer = new Customer("Test Customer", "12345678901", address);

        when(customerRepository.save(any(Customer.class))).thenReturn(mockCustomer);
        AddressDTO addressDTO = new AddressDTO("Rua Teste", "Cidade Teste", "Estado Teste", "12345-678", "Brasil");
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Test Customer");
        customerDTO.setDocument("12345678901");
        customerDTO.setDeliveryAddress(addressDTO);

        Customer customer = customerService.createCustomer(customerDTO);

        assertNotNull(customer);
        assertEquals("Test Customer", customer.getName());
    }

    @Test
    void testFindByDocument() {
        Customer mockCustomer = new Customer("Test Customer", "12345678901", null);

        when(customerRepository.findByDocument("12345678901")).thenReturn(Optional.of(mockCustomer));

        Customer customer = customerService.findByDocument("12345678901");

        assertNotNull(customer);
        assertEquals("Test Customer", customer.getName());
    }

    @Test
    void testFindByDocumentNotFound() {
        when(customerRepository.findByDocument("99999999999")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            customerService.findByDocument("99999999999");
        });

        assertEquals("Customer not found", exception.getMessage());
    }
}

