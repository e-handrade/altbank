package br.com.altbank.security;


import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@Provider
@RequiresApiKey
public class ApiKeyFilter implements ContainerRequestFilter {

    @ConfigProperty(name = "altbank.api.key")
    String apiKey;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String headerApiKey = containerRequestContext.getHeaderString("X-API-KEY");

        if (headerApiKey == null || !headerApiKey.equals(apiKey)) {
            containerRequestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("API Key is missing or invalid")
                    .build());
        }
    }
}
