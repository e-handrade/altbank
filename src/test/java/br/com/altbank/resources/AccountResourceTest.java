package br.com.altbank.resources;

import br.com.altbank.dto.AccountDTO;
import br.com.altbank.entity.Account;
import br.com.altbank.service.AccountService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountResourceTest {

    AccountResource accountResource;
    AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = Mockito.mock(AccountService.class);
        accountResource = new AccountResource();
        accountResource.accountService = accountService; // Manual injection
    }

    @Test
    void testCreateAccount() {
        Account mockAccount = new Account(null, new BigDecimal("1000.00"));
        when(accountService.createAccount(Mockito.any(AccountDTO.class))).thenReturn(mockAccount);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setBalance(new BigDecimal("1000.00"));

        Response response = accountResource.createAccount(accountDTO);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
    }

    @Test
    void testCancelAccount() {
        doNothing().when(accountService).cancelAccount("12345678901");

        Response response = accountResource.cancelAccount("12345678901");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("12345678901", response.getEntity());
    }
}

