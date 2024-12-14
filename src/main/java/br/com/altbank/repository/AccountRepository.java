package br.com.altbank.repository;

import br.com.altbank.entity.Account;
import br.com.altbank.enums.AccountStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.Optional;

@ApplicationScoped
public class AccountRepository {

    @Inject
    EntityManager entityManager;

    public Account save(Account account) {
        entityManager.persist(account);
        return account;
    }

    @Transactional
    public void cancelAccount(Long accountId, String reason) {
        Account account = entityManager.find(Account.class, accountId);
        if (account != null) {
            account.setStatus(AccountStatus.CANCELLED);
        }
    }

    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Account.class, id));
    }

    public Optional<Account> findByDocument(String document) {
        return entityManager.createQuery(
                        "SELECT a FROM Account a " +
                                "JOIN Customer c ON a.customerId = c.id " +
                                "WHERE c.document = :document", Account.class)
                .setParameter("document", document)
                .getResultStream()
                .findFirst();
    }

    @Transactional
    public void cancelAccount(String document) {
        Account account = findByDocument(document).orElseThrow(() -> new NotFoundException("Account not found"));
        account.setStatus(AccountStatus.CANCELLED);
    }

}
