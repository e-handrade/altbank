package br.com.altbank.resources;

import br.com.altbank.dto.CardDTO;
import br.com.altbank.entity.Card;
import br.com.altbank.service.CardService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardResourceTest {

    CardResource cardResource;
    CardService cardService;

    @BeforeEach
    void setUp() {
        cardService = Mockito.mock(CardService.class);
        cardResource = new CardResource();
        cardResource.cardService = cardService; // Manual injection
    }

    @Test
    void testCreatePhysicalCard() {
        Card mockCard = Mockito.mock(Card.class);
        when(cardService.createPhysicalCard(Mockito.any(CardDTO.class))).thenReturn(mockCard);

        CardDTO cardDTO = new CardDTO();

        Response response = cardResource.createPhysicalCard(cardDTO);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
    }

    @Test
    void testUnblockCard() {
        doNothing().when(cardService).unblockCard(1L);

        Response response = cardResource.unblockCard(1L);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}

