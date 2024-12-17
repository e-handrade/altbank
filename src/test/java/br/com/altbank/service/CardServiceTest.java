package br.com.altbank.service;

import br.com.altbank.dto.CardDTO;
import br.com.altbank.entity.Account;
import br.com.altbank.entity.Card;
import br.com.altbank.enums.CardType;
import br.com.altbank.repository.CardRepository;
import br.com.altbank.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CardServiceTest {

    CardRepository cardRepository;
    AccountService accountService;
    CardService cardService;

    @BeforeEach
    void setUp() {
        cardRepository = Mockito.mock(CardRepository.class);
        accountService = Mockito.mock(AccountService.class);

        cardService = new CardService();
        cardService.cardRepository = cardRepository;
        cardService.accountService = accountService;
    }

    @Test
    void testCreatePhysicalCard() {
        Account mockAccount = new Account(null, new BigDecimal("1000.00"));
        Card mockCard = new Card(mockAccount, CardType.PHYSICAL);

        when(accountService.findById(1L)).thenReturn(mockAccount);
        when(cardRepository.save(any(Card.class))).thenReturn(mockCard);

        CardDTO cardDTO = new CardDTO();
        cardDTO.setAccountId(1L);
        cardDTO.setCardType("PHYSICAL");

        Card card = cardService.createPhysicalCard(cardDTO);

        assertNotNull(card);
        assertEquals(CardType.PHYSICAL, card.getCardType());
    }
}

