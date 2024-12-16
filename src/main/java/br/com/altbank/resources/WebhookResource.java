package br.com.altbank.resources;

import br.com.altbank.dto.CvvWebhookDTO;
import br.com.altbank.dto.DeliveryWebhookDTO;
import br.com.altbank.security.RequiresApiKey;
import br.com.altbank.service.WebhookService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/webhooks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WebhookResource {

    @Inject
    WebhookService webhookService;

    @POST
    @Path("/delivery")
    @RequiresApiKey
    @Operation(summary = "Processa a entrega do cartão", description = "Recebe o webhook da transportadora com o status de entrega do cartão.")
    public Response handleDeliveryWebhook(DeliveryWebhookDTO deliveryWebhookDTO) {
        webhookService.processDeliveryWebhook(deliveryWebhookDTO);
        return Response.ok("Delivery webhook processed").build();
    }

    @POST
    @Path("/cvv")
    @RequiresApiKey
    @Operation(summary = "Atualiza o CVV do cartão virtual", description = "Recebe o webhook da processadora de cartões com o novo CVV e a data de expiração do cartão virtual.")
    public Response handleCvvWebhook(CvvWebhookDTO cvvWebhookDTO) {
        webhookService.processCvvWebhook(cvvWebhookDTO);
        return Response.ok("CVV webhook processed").build();
    }

    @GET
    @Path("/hello")
    @RequiresApiKey
    public Response hello() {
        return Response.ok("Hello, Webhooks!").build();
    }


}
