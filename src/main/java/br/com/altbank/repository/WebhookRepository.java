package br.com.altbank.repository;

import br.com.altbank.dto.CvvWebhookDTO;
import br.com.altbank.dto.DeliveryWebhookDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class WebhookRepository {
    @Inject
    EntityManager entityManager;

    public void logDeliveryWebhook(DeliveryWebhookDTO deliveryWebhookDTO) {
        entityManager.persist(deliveryWebhookDTO);
    }

    public void logDeliveryError(DeliveryWebhookDTO deliveryWebhookDTO) {
        entityManager.persist(deliveryWebhookDTO);
    }

    public void logCvvWebhook(CvvWebhookDTO cvvWebhookDTO) {
        entityManager.persist(cvvWebhookDTO);
    }

}
