package br.com.altbank.entity;

import br.com.altbank.entity.*;
import br.com.altbank.enums.CardType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class CvvWebhookLogTest {

    @Test
    void testCvvWebhookLogPrePersist() {
        CvvWebhookLog log = new CvvWebhookLog();
        log.onPersist();

        Assertions.assertNotNull(log.getReceivedAt(), "Received at should not be null");
    }

    @Test
    void testCvvWebhookLogProperties() {
        Account account = new Account();
        Card card = new Card(account, CardType.PHYSICAL);
        CvvWebhookLog log = new CvvWebhookLog();

        log.setAccount(account);
        log.setCard(card);
        log.setNextCvv(123);
        log.setExpirationDate(LocalDateTime.now());

        Assertions.assertEquals(account, log.getAccount(), "Account should match");
        Assertions.assertEquals(card, log.getCard(), "Card should match");
        Assertions.assertEquals(123, log.getNextCvv(), "Next CVV should match");
        Assertions.assertNotNull(log.getExpirationDate(), "Expiration date should not be null");
    }
}

