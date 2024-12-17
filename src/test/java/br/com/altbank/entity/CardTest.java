package br.com.altbank.entity;

import br.com.altbank.enums.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void testCardPrePersist() {
        Account account = new Account();
        Card card = new Card(account, CardType.PHYSICAL);

        card.onCreate();

        Assertions.assertNotNull(card.getExpirationDate(), "Expiration date should not be null");
        Assertions.assertEquals(DeliveryStatus.PENDING, card.getDeliveryStatus(), "Delivery status should be PENDING");
        Assertions.assertEquals(CardStatus.BLOCKED, card.getStatus(), "Card status should be BLOCKED");
        Assertions.assertNotNull(card.getCvv(), "CVV should be generated");
        Assertions.assertNotNull(card.getNumber(), "Card number should be generated");
    }

    @Test
    void testCardProperties() {
        Account account = new Account();
        Card card = new Card(account, CardType.VIRTUAL);

        card.setStatus(CardStatus.ACTIVE);
        card.setDeliveryStatus(DeliveryStatus.DELIVERED);

        Assertions.assertEquals(CardType.VIRTUAL, card.getCardType(), "Card type should be VIRTUAL");
        Assertions.assertEquals(CardStatus.ACTIVE, card.getStatus(), "Card status should be ACTIVE");
        Assertions.assertEquals(DeliveryStatus.DELIVERED, card.getDeliveryStatus(), "Delivery status should be DELIVERED");
    }
}
