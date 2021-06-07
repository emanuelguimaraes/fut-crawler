package br.com.fifa.futcrawler.domain.card;

import br.com.fifa.futcrawler.application.card.request.OverallsRequest;
import br.com.fifa.futcrawler.domain.card.dto.CardDTO;

import java.util.List;
import java.util.Optional;

public interface CardRepository {

    Optional<Card> findById(Long id);

    List<CardDTO> findAllByAttributesType(OverallsRequest request);

    void save(Card card);

    Optional<Card> findByNameAndRevision(String name, String revision);

    List<Card> findByResourceId(Long idResource);
}
