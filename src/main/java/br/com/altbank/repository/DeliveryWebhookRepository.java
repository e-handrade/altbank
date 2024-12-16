package br.com.altbank.repository;

import br.com.altbank.entity.DeliveryWebhookLog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeliveryWebhookRepository {

    @Inject
    EntityManager entityManager;
    @Transactional
    public void save(DeliveryWebhookLog log) {
        entityManager.persist(log);
    }
}
