package br.com.altbank.resources;

import br.com.altbank.dto.CvvWebhookDTO;
import br.com.altbank.dto.DeliveryWebhookDTO;
import br.com.altbank.security.RequiresApiKey;
import br.com.altbank.service.WebhookService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/webhooks")
@Consumes(MediaType.APPLICATION_JSON)
public class WebhookResource {

    @Inject
    WebhookService webhookService;

    @POST
    @Path("/delivery")
    @RequiresApiKey
    public Response deliveryWebhook(DeliveryWebhookDTO deliveryWebhookDTO) {
        webhookService.processDeliveryWebhook(deliveryWebhookDTO);
        return Response.ok().build();
    }

    @POST
    @Path("/cvv")
    @RequiresApiKey
    public Response cvvWebhook(CvvWebhookDTO cvvWebhookDTO) {
        webhookService.processCvvWebhook(cvvWebhookDTO);
        return Response.ok().build();
    }

    @GET
    public String hello() {
        return "Hello, API Key!";
    }

}
