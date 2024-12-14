package br.com.altbank.resources;

import br.com.altbank.dto.AccountDTO;
import br.com.altbank.entity.Account;
import br.com.altbank.security.RequiresApiKey;
import br.com.altbank.service.AccountService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    AccountService accountService;

    @POST
    public Response createAccount(AccountDTO accountDTO) {
        Account account = accountService.createAccount(accountDTO);
        return Response.status(Response.Status.CREATED).entity(account).build();
    }

    @PUT
    @Path("/{id}/cancel")
    public Response cancelAccount(@PathParam("document") String document) {
        accountService.cancelAccount(document);
        return Response.noContent().build();
    }

}
