package br.com.altbank.repository;

import br.com.altbank.entity.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class CustomerRepository{

    @Inject
    EntityManager entityManager;

    @Transactional
    public Customer save(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }

    @Transactional
    public void delete(Long id) {
        Customer customer = entityManager.find(Customer.class, id);
        if (customer != null) {
            entityManager.remove(customer);
        }
    }

    public Optional<Customer> findByDocument(String document) {
        return entityManager.createQuery(
                        "SELECT c FROM Customer c WHERE c.document = :document", Customer.class)
                .setParameter("document", document)
                .getResultStream()
                .findFirst();
    }
}
