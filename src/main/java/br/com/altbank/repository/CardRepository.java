package br.com.altbank.repository;

import br.com.altbank.entity.Card;
import br.com.altbank.entity.CardReissueRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.Optional;

@ApplicationScoped
public class CardRepository {
    @Inject
    EntityManager entityManager;

    public Card save(Card card) {
        entityManager.persist(card);
        return card;
    }

    public void update(Card card) {
        entityManager.merge(card);
    }

    public Optional<Card> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Card.class, id));
    }

    public Optional<Card> findPhysicalCardActiveByDocumentCustomer(String document) {
        return entityManager.createQuery(
                        "SELECT c FROM Card c " +
                                "JOIN Account a ON c.accountId = a.id " +
                                "JOIN Customer cu ON a.customerId = cu.id " +
                                "WHERE cu.document = :document AND c.cardType = 'PHYSICAL' AND c.status = 'ACTIVE'", Card.class)
                .setParameter("document", document)
                .getResultStream()
                .findFirst();
    }

    public void saveCardReissueRequest(CardReissueRequest request) {
        entityManager.persist(request);
    }
}
