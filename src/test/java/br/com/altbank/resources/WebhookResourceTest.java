package br.com.altbank.resources;

import br.com.altbank.dto.CvvWebhookDTO;
import br.com.altbank.dto.DeliveryWebhookDTO;
import br.com.altbank.service.WebhookService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WebhookResourceTest {

    WebhookResource webhookResource;
    WebhookService webhookService;

    @BeforeEach
    void setUp() {
        webhookService = Mockito.mock(WebhookService.class);
        webhookResource = new WebhookResource();
        webhookResource.webhookService = webhookService; // Manual injection
    }

    @Test
    void testHandleDeliveryWebhook() {
        doNothing().when(webhookService).processDeliveryWebhook(Mockito.any(DeliveryWebhookDTO.class));

        DeliveryWebhookDTO deliveryWebhookDTO = new DeliveryWebhookDTO();
        Response response = webhookResource.handleDeliveryWebhook(deliveryWebhookDTO);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void testHandleCvvWebhook() {
        doNothing().when(webhookService).processCvvWebhook(Mockito.any(CvvWebhookDTO.class));

        CvvWebhookDTO cvvWebhookDTO = new CvvWebhookDTO();
        Response response = webhookResource.handleCvvWebhook(cvvWebhookDTO);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void testHello() {
        Response response = webhookResource.hello();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Hello, Webhooks!", response.getEntity());
    }
}
