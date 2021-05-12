package br.com.fifa.futcrawler.application.card;

import br.com.fifa.futcrawler.application.Command;
import br.com.fifa.futcrawler.application.crawler.dto.CardDetailsDTO;
import br.com.fifa.futcrawler.domain.card.Card;
import br.com.fifa.futcrawler.domain.card.CardRepository;

public class SaveCard extends Command<CardDetailsDTO> {

    public final CardRepository repository;

    public SaveCard(CardRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(CardDetailsDTO dto) {
        Card card = dto.parseFromCartao();
        repository.save(card);
    }
}
