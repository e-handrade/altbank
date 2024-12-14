package br.com.altbank.service;

import br.com.altbank.dto.AccountDTO;
import br.com.altbank.entity.*;
import br.com.altbank.repository.AccountRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.math.BigDecimal;

@ApplicationScoped
public class AccountService {
    @Inject
    AccountRepository accountRepository;

    @Inject
    CustomerService customerService;

    public Account createAccount(AccountDTO accountDTO) {
        Customer customer = customerService.findByDocument(accountDTO.getDocument());
        Account account = new Account(customer, BigDecimal.valueOf(1500));
        return accountRepository.save(account);
    }

    public Account findById(Long id){
        return accountRepository.findById(id).orElseThrow(() -> new NotFoundException("Account not found"));
    }

    public Account findByDocument(String document){
        return accountRepository.findByDocument(document).orElseThrow(() -> new NotFoundException("Account not found"));
    }

    public void cancelAccount(String document) {
        accountRepository.cancelAccount(document);
    }

}
