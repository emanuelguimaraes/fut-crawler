package br.com.fifa.futcrawler.application.card;

import br.com.fifa.futcrawler.application.crawler.dto.CardDetailsDTO;
import br.com.fifa.futcrawler.domain.card.Card;
import br.com.fifa.futcrawler.domain.card.CardRepository;

public class SaveCard {

    private final CardRepository repository;

    public SaveCard(CardRepository repository) {
        this.repository = repository;
    }

    public void execute(CardDetailsDTO dto) {
        Card card = dto.parseFromCard();
        repository.save(card);
    }
}
