package br.com.altbank.service;

import br.com.altbank.dto.CardDTO;
import br.com.altbank.dto.ReissueCardDTO;
import br.com.altbank.entity.*;
import br.com.altbank.enums.*;
import br.com.altbank.repository.CardRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CardService {
    @Inject
    CardRepository cardRepository;
    @Inject
    AccountService accountService;

    public Card createPhysicalCard(CardDTO cardDTO) {
        Account account = accountService.findById(cardDTO.getAccountId());
        Card card = new Card(account, CardType.PHYSICAL);
        return cardRepository.save(card);
    }

    public void reissueCard(Long id, ReissueCardDTO reissueCardDTO) {
        Card card = cardRepository.findById(id).orElseThrow(() -> new NotFoundException("Card not found"));
        CardReissueRequest request = new CardReissueRequest(card, ReissueReason.valueOf(reissueCardDTO.getReason()));
        cardRepository.saveCardReissueRequest(request);

        card.setStatus(CardStatus.BLOCKED);
        cardRepository.update(card);

        CardDTO cardDTO = new CardDTO();
        cardDTO.setAccountId(card.getAccount().getId());
        createPhysicalCard(cardDTO);

    }

    public Card createVirtualCard(String document) {
        Card physicalCard = cardRepository.findPhysicalCardActiveByDocumentCustomer(document).orElseThrow(() -> new NotFoundException("Not physical card "));
        Account account = accountService.findByDocument(document);
        Card card = new Card(account, CardType.VIRTUAL);
        return cardRepository.save(card);
    }
}
