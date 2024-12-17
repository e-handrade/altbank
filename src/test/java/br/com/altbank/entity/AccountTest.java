package br.com.altbank.entity;

import br.com.altbank.enums.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class AccountTest {

    @Test
    void testAccountPrePersist() {
        Account account = new Account();
        account.onCreate();

        Assertions.assertNotNull(account.getCreatedAt(), "Created at should not be null");
        Assertions.assertEquals(AccountStatus.ACTIVE, account.getStatus(), "Account status should be ACTIVE");
    }

    @Test
    void testAccountProperties() {
        Customer customer = new Customer();
        BigDecimal balance = new BigDecimal("1000.00");
        Account account = new Account(customer, balance);

        account.setStatus(AccountStatus.CANCELLED);

        Assertions.assertEquals(customer, account.getCustomer(), "Customer should match");
        Assertions.assertEquals(balance, account.getBalance(), "Balance should match");
        Assertions.assertEquals(AccountStatus.CANCELLED, account.getStatus(), "Account status should be CANCELLED");
    }
}
