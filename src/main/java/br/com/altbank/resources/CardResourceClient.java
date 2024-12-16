package br.com.altbank.resources;

import br.com.altbank.dto.CardDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8080")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface CardResourceClient {
    @POST
    @Path("/cards")
    Response createPhysicalCard(CardDTO cardDTO);
}
