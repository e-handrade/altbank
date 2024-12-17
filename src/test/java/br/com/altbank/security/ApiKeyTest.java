package br.com.altbank.security;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ApiKeyFilterTest {

    private ApiKeyFilter apiKeyFilter;
    private ContainerRequestContext containerRequestContext;

    @BeforeEach
    void setUp() {
        apiKeyFilter = new ApiKeyFilter();
        apiKeyFilter.apiKey = "valid-api-key"; // Configura a API Key esperada
        containerRequestContext = Mockito.mock(ContainerRequestContext.class);
    }

    @Test
    void testFilterWithValidApiKey() throws IOException {
        // Simula o header contendo a API Key correta
        when(containerRequestContext.getHeaderString("X-API-KEY")).thenReturn("valid-api-key");

        // Chama o método filter
        apiKeyFilter.filter(containerRequestContext);

        // Verifica que o método abortWith NÃO foi chamado
        verify(containerRequestContext, never()).abortWith(any(Response.class));
    }

    @Test
    void testFilterWithInvalidApiKey() throws IOException {
        // Simula o header contendo uma API Key incorreta
        when(containerRequestContext.getHeaderString("X-API-KEY")).thenReturn("invalid-api-key");

        // Chama o método filter
        apiKeyFilter.filter(containerRequestContext);

        // Captura o argumento passado para o método abortWith
        var responseCaptor = ArgumentCaptor.forClass(Response.class);
        verify(containerRequestContext).abortWith(responseCaptor.capture());

        // Verifica que a resposta contém o status UNAUTHORIZED
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), responseCaptor.getValue().getStatus());
        assertEquals("API Key is missing or invalid", responseCaptor.getValue().getEntity());
    }

    @Test
    void testFilterWithMissingApiKey() throws IOException {
        // Simula ausência do header "X-API-KEY"
        when(containerRequestContext.getHeaderString("X-API-KEY")).thenReturn(null);

        // Chama o método filter
        apiKeyFilter.filter(containerRequestContext);

        // Captura o argumento passado para o método abortWith
        var responseCaptor = ArgumentCaptor.forClass(Response.class);
        verify(containerRequestContext).abortWith(responseCaptor.capture());

        // Verifica que a resposta contém o status UNAUTHORIZED
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), responseCaptor.getValue().getStatus());
        assertEquals("API Key is missing or invalid", responseCaptor.getValue().getEntity());
    }
}
