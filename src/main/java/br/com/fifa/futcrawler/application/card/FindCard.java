package br.com.fifa.futcrawler.application.card;

import br.com.fifa.futcrawler.domain.card.CardRepository;
import br.com.fifa.futcrawler.domain.card.dto.CardDTO;
import br.com.fifa.futcrawler.domain.card.dto.CardFilterDTO;

import java.util.List;

public class FindCard {

    private final CardRepository repository;

    public FindCard(CardRepository repository) {
        this.repository = repository;
    }

    public List<CardDTO> execute(CardFilterDTO filterDTO) {
        return repository.find(filterDTO);
    }
}
