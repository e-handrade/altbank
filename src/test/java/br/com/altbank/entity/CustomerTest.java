package br.com.altbank.entity;

import br.com.altbank.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class CustomerTest {

    @Test
    void testCustomerPrePersist() {
        Customer customer = new Customer();
        customer.onCreate();

        Assertions.assertNotNull(customer.getCreatedAt(), "Created at should not be null");
    }

    @Test
    void testCustomerProperties() {
        Address address = new Address("Street Test", "City Test", "State Test", "12345-678", "Country Test");
        Customer customer = new Customer("Customer Test", "123456789", address);

        Assertions.assertEquals("Customer Test", customer.getName(), "Customer name should match");
        Assertions.assertEquals("123456789", customer.getDocument(), "Customer document should match");
        Assertions.assertEquals(address, customer.getDeliveryAddress(), "Delivery address should match");
    }
}
