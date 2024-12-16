package br.com.altbank.resources;

import br.com.altbank.dto.CardDTO;
import br.com.altbank.dto.ReissueCardDTO;
import br.com.altbank.entity.Card;
import br.com.altbank.security.RequiresApiKey;
import br.com.altbank.service.CardService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CardResource {

    @Inject
    CardService cardService;

    @POST
    public Response createPhysicalCard(CardDTO cardDTO) {
        System.out.println("====================== CHEGUEI =====================================");
        Card card = cardService.createPhysicalCard(cardDTO);
        return Response.status(Response.Status.CREATED).entity(card).build();
    }

    @POST
    @Path("/reissue/{id}")
    public Response reissueCard(@PathParam("id") Long id, ReissueCardDTO reissueCardDTO) {
        cardService.reissueCard(id, reissueCardDTO);
        return Response.ok().build();
    }

    @POST
    @Path("/virtual/{document}")
    public Response createVirtualCard(@PathParam("document") String document) {
        Card card = cardService.createVirtualCard(document);
        return Response.status(Response.Status.CREATED).entity(card).build();
    }

    @PUT
    @Path("/unblock/{id}")
    public Response unblockCard(@PathParam("id") Long id) {

        try {
            cardService.unblockCard(id);
            return Response.status(Response.Status.OK).build();
        } catch (IllegalArgumentException iae) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

    }

}
