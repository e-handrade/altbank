package br.com.altbank.service;

import br.com.altbank.dto.CvvWebhookDTO;
import br.com.altbank.dto.DeliveryWebhookDTO;
import br.com.altbank.entity.*;
import br.com.altbank.enums.CardStatus;
import br.com.altbank.enums.DeliveryStatus;
import br.com.altbank.repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.Optional;

@ApplicationScoped
public class WebhookService {

    @Inject
    CardRepository cardRepository;

    @Inject
    AccountRepository accountRepository;

    @Inject
    DeliveryWebhookRepository deliveryWebhookRepository;

    @Inject
    CvvWebhookRepository cvvWebhookRepository;

    public void processDeliveryWebhook(DeliveryWebhookDTO deliveryWebhookDTO) {

        // Log delivery webhook
        DeliveryWebhookLog log = new DeliveryWebhookLog();
        log.setTrackingId(deliveryWebhookDTO.getTrackingId());
        log.setDeliveryStatus(deliveryWebhookDTO.getDeliveryStatus());
        log.setDeliveryDate(deliveryWebhookDTO.getDeliveryDate());
        log.setDeliveryReturnReason(deliveryWebhookDTO.getDeliveryReturnReason());
        log.setDeliveryAddress(deliveryWebhookDTO.getDeliveryAddress());
        deliveryWebhookRepository.save(log);

        if ("DELIVERED".equalsIgnoreCase(deliveryWebhookDTO.getDeliveryStatus())) {

            // Update card delivery status
            Card card = cardRepository.findById(Long.parseLong(deliveryWebhookDTO.getTrackingId())).orElseThrow(() -> new NotFoundException("Card not found"));

            card.setDeliveryStatus(DeliveryStatus.DELIVERED);
            cardRepository.update(card);
        }

    }

    public void processCvvWebhook(CvvWebhookDTO cvvWebhookDTO) {

        Card card = cardRepository.findById(cvvWebhookDTO.getCardId())
                .orElseThrow(() -> new NotFoundException("Card not found"));

        Account account = accountRepository.findById(cvvWebhookDTO.getAccountId())
                .orElseThrow(() -> new NotFoundException("Card not found"));

        // Log CVV webhook
        CvvWebhookLog log = new CvvWebhookLog();
        log.setAccount(account);
        log.setCard(card);
        log.setNextCvv(cvvWebhookDTO.getNextCvv());
        log.setExpirationDate(cvvWebhookDTO.getExpirationDate());
        cvvWebhookRepository.save(log);

        // Update card CVV
        card.setCvv(cvvWebhookDTO.getNextCvv());
        card.setExpirationDate(cvvWebhookDTO.getExpirationDate());
        cardRepository.update(card);
    }
}
