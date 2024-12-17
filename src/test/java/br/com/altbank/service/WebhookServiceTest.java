package br.com.altbank.service;

import br.com.altbank.dto.DeliveryWebhookDTO;
import br.com.altbank.entity.Card;
import br.com.altbank.repository.CardRepository;
import br.com.altbank.repository.DeliveryWebhookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class WebhookServiceTest {

    CardRepository cardRepository;
    DeliveryWebhookRepository deliveryWebhookRepository;
    WebhookService webhookService;

    @BeforeEach
    void setUp() {
        cardRepository = Mockito.mock(CardRepository.class);
        deliveryWebhookRepository = Mockito.mock(DeliveryWebhookRepository.class);

        webhookService = new WebhookService();
        webhookService.cardRepository = cardRepository;
        webhookService.deliveryWebhookRepository = deliveryWebhookRepository;
    }

    @Test
    void testProcessDeliveryWebhook() {
        DeliveryWebhookDTO deliveryWebhookDTO = new DeliveryWebhookDTO();
        deliveryWebhookDTO.setTrackingId("123");
        deliveryWebhookDTO.setDeliveryStatus("DELIVERED");

        Card mockCard = new Card(null, null);
        when(cardRepository.findById(123L)).thenReturn(Optional.of(mockCard));

        webhookService.processDeliveryWebhook(deliveryWebhookDTO);

        verify(cardRepository, times(1)).update(any(Card.class));
    }
}
