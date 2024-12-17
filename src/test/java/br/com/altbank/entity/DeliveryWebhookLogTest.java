package br.com.altbank.entity;

import br.com.altbank.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class DeliveryWebhookLogTest {

    @Test
    void testDeliveryWebhookLogPrePersist() {
        DeliveryWebhookLog log = new DeliveryWebhookLog();
        log.onPersist();

        Assertions.assertNotNull(log.getReceivedAt(), "Received at should not be null");
    }

    @Test
    void testDeliveryWebhookLogProperties() {
        DeliveryWebhookLog log = new DeliveryWebhookLog();
        log.setTrackingId("TRACK123");
        log.setDeliveryStatus("DELIVERED");
        log.setDeliveryDate(LocalDateTime.now());
        log.setDeliveryReturnReason("Address not found");
        log.setDeliveryAddress("123 Test Street");

        Assertions.assertEquals("TRACK123", log.getTrackingId(), "Tracking ID should match");
        Assertions.assertEquals("DELIVERED", log.getDeliveryStatus(), "Delivery status should match");
        Assertions.assertEquals("Address not found", log.getDeliveryReturnReason(), "Delivery return reason should match");
        Assertions.assertEquals("123 Test Street", log.getDeliveryAddress(), "Delivery address should match");
        Assertions.assertNotNull(log.getDeliveryDate(), "Delivery date should not be null");
    }
}

