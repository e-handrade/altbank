package br.com.altbank.service;

import br.com.altbank.dto.AccountDTO;
import br.com.altbank.entity.Account;
import br.com.altbank.entity.Customer;
import br.com.altbank.repository.AccountRepository;
import br.com.altbank.resources.CardResourceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    AccountRepository accountRepository;
    CustomerService customerService;
    AccountService accountService;
    CardResourceClient cardResourceClient;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);
        customerService = Mockito.mock(CustomerService.class);
        cardResourceClient = Mockito.mock(CardResourceClient.class);

        accountService = new AccountService();
        accountService.accountRepository = accountRepository;
        accountService.customerService = customerService;
        accountService.cardResourceClient = cardResourceClient; // Certifique-se de atribuir aqui
    }

    @Test
    void testCreateAccount() {
        Customer mockCustomer = new Customer("Test Customer", "12345678901", null);
        Account mockAccount = new Account(mockCustomer, new BigDecimal("1000.00"));

        when(customerService.findByDocument("12345678901")).thenReturn(mockCustomer);
        when(accountRepository.save(any(Account.class))).thenReturn(mockAccount);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setDocument("12345678901");
        accountDTO.setBalance(new BigDecimal("1000.00"));

        Account account = accountService.createAccount(accountDTO);

        assertNotNull(account);
        assertEquals(mockCustomer, account.getCustomer());
    }

    @Test
    void testFindById() {
        Account mockAccount = new Account(null, new BigDecimal("500.00"));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(mockAccount));

        Account account = accountService.findById(1L);

        assertNotNull(account);
        assertEquals(new BigDecimal("500.00"), account.getBalance());
    }

    @Test
    void testCancelAccount() {
        doNothing().when(accountRepository).cancelAccount("12345678901");

        accountService.cancelAccount("12345678901");

        verify(accountRepository, times(1)).cancelAccount("12345678901");
    }
}

