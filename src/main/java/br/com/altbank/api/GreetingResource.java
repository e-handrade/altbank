package br.com.altbank.api;

import br.com.altbank.security.RequiresApiKey;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @RequiresApiKey
    public String hello() {
        return "Hello from Quarkus REST";
    }
}
