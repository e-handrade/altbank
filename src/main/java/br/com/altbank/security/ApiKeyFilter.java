package br.com.altbank.security;


import br.com.altbank.config.ConfigLoader;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@Provider
@RequiresApiKey
public class ApiKeyFilter implements ContainerRequestFilter {

    //String apiKey = System.getenv("X_API_KEY");
    String apiKey = ConfigLoader.getEnv("altbank.api.key");

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        String headerApiKey = containerRequestContext.getHeaderString("X-API-KEY");

        System.out.println("API Key recebida: " + apiKey);
        System.out.println("API Key esperada: " + headerApiKey);

        if (headerApiKey == null || !headerApiKey.equals(apiKey)) {
            containerRequestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("API Key is missing or invalid")
                    .build());
        }
    }
}
