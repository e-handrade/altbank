package br.com.altbank.resources;

import br.com.altbank.dto.CustomerDTO;
import br.com.altbank.entity.Customer;
import br.com.altbank.security.RequiresApiKey;
import br.com.altbank.service.CustomerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerService customerService;

    @POST
    public Response createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerService.createCustomer(customerDTO);
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }

//    @DELETE
//    @Path("/{id}")
//    public Response deleteCustomer(@PathParam("id") Long id) {
//        customerService.deleteCustomer(id);
//        return Response.noContent().build();
//    }
}
