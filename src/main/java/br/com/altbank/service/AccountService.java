package br.com.altbank.service;

import br.com.altbank.dto.AccountDTO;
import br.com.altbank.dto.CardDTO;
import br.com.altbank.entity.Account;
import br.com.altbank.entity.Customer;
import br.com.altbank.repository.AccountRepository;
import br.com.altbank.resources.CardResourceClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigDecimal;

@ApplicationScoped
public class AccountService {
    @Inject
    AccountRepository accountRepository;

    @Inject
    CustomerService customerService;

    @Inject
    @RestClient
    CardResourceClient cardResourceClient;

    public Account createAccount(AccountDTO accountDTO) {

        Customer customer = customerService.findByDocument(accountDTO.getDocument());
        Account account = new Account(customer, accountDTO.getBalance());
        account = accountRepository.save(account);

        // Cria o cartão físico para a conta
        CardDTO cardDTO = new CardDTO();
        cardDTO.setAccountId(account.getId());
        cardDTO.setCardType("PHYSICAL");
        cardResourceClient.createPhysicalCard(cardDTO);

        return account;

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
