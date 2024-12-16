package br.com.altbank.repository;

import br.com.altbank.entity.CvvWebhookLog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CvvWebhookRepository {

    @Inject
    EntityManager entityManager;
    @Transactional
    public void save(CvvWebhookLog log) {
        entityManager.persist(log);
    }
}
