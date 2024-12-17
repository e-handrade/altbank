package br.com.altbank.resources;

import br.com.altbank.dto.CustomerDTO;
import br.com.altbank.entity.Customer;
import br.com.altbank.service.CustomerService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerResourceTest {

    CustomerResource customerResource;
    CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = Mockito.mock(CustomerService.class);
        customerResource = new CustomerResource();
        customerResource.customerService = customerService; // Manual injection
    }

    @Test
    void testCreateCustomer() {
        Customer mockCustomer = new Customer("Test Name", "12345678901", null);
        when(customerService.createCustomer(Mockito.any(CustomerDTO.class))).thenReturn(mockCustomer);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Test Name");
        customerDTO.setDocument("12345678901");

        Response response = customerResource.createCustomer(customerDTO);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
    }
}
