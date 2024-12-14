package br.com.altbank.service;

import br.com.altbank.dto.CvvWebhookDTO;
import br.com.altbank.dto.DeliveryWebhookDTO;
import br.com.altbank.entity.Card;
import br.com.altbank.enums.DeliveryStatus;
import br.com.altbank.repository.CardRepository;
import br.com.altbank.repository.WebhookRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class WebhookService {
    @Inject
    WebhookRepository webhookRepository;
    @Inject
    CardRepository cardRepository;

    public void processDeliveryWebhook(DeliveryWebhookDTO deliveryWebhookDTO) {
        if ("DELIVERED".equals(deliveryWebhookDTO.getDeliveryStatus())) {
            Card card = cardRepository.findById(Long.parseLong(deliveryWebhookDTO.getTrackingId()))
                    .orElseThrow(() -> new NotFoundException("Card not found"));
            card.setDeliveryStatus(DeliveryStatus.DELIVERED);
            cardRepository.update(card);
            webhookRepository.logDeliveryWebhook(deliveryWebhookDTO);
        } else {
            webhookRepository.logDeliveryError(deliveryWebhookDTO);
        }
    }

    public void processCvvWebhook(CvvWebhookDTO cvvWebhookDTO) {
        Card card = cardRepository.findById(Long.parseLong(cvvWebhookDTO.getCardId()))
                .orElseThrow(() -> new NotFoundException("Card not found"));
        card.setCvv(cvvWebhookDTO.getNextCvv());
        card.setExpirationDate(cvvWebhookDTO.getExpirationDate());
        cardRepository.update(card);
        webhookRepository.logCvvWebhook(cvvWebhookDTO);
    }
}
